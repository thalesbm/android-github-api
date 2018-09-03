package br.tbm.github.api.controllers;

import android.support.annotation.NonNull;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.R;
import br.tbm.github.api.interfaces.ControllerCallbacks;
import br.tbm.github.api.interfaces.TasksCallbacks;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.rest.RestUser;
import br.tbm.github.api.tasks.SaveGithubUserTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class SearchByUsernameController implements TasksCallbacks.SaveGithubUserTaskCallback {

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    private final boolean closeActivity = false;

    private ControllerCallbacks<Profile> mCallback;

    public SearchByUsernameController(ControllerCallbacks<Profile> callback) {
        this.mCallback = callback;
    }

    /**
     * Metodo para pesquisar um perfil pelo nome
     *
     * @param profileName String
     */
    public void search(String profileName) {
        RestUser service = GithubApplication.getRetrofitInstance().create(RestUser.class);
        Call<Profile> responseCall = service.getProfile(profileName);
        responseCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                searchProfileResponseSuccess(response);
            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                mCallback.displayAlertDialog(t.getMessage(), closeActivity);
            }
        });
    }

    // ####################
    // CALLBACK DO SERVIDOR
    // ####################

    /**
     * Metodo responsavel pela logica de sucesso da chamada de pesquisar pelo username
     *
     * @param response Response<Profile>
     */
    private void searchProfileResponseSuccess(final Response<Profile> response) {
        if (response.isSuccessful()) {

            if (response.body() != null && response.body().getName() != null) {
                new SaveGithubUserTask(this).execute(response.body());
            } else {
                mCallback.displayAlertDialog(R.string.search_activity_user_not_found, closeActivity);
            }
        } else {
            mCallback.networkIssue(response.raw().code(), closeActivity);
        }
    }

    // ################
    // CALLBACK DA TASK
    // ################

    @Override
    public void saveGithubUserTaskSuccess(Profile profile) {
        mCallback.success(profile);
    }

    @Override
    public void saveGithubUserTaskFailure() {
        mCallback.displayAlertDialog(R.string.generic_database_issue, closeActivity);
    }
}
