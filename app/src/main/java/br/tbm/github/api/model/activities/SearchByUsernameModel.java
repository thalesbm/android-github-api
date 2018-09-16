package br.tbm.github.api.model.activities;

import android.support.annotation.NonNull;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.R;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.interfaces.activities.SearchByUsernameMVP;
import br.tbm.github.api.interfaces.generic.TasksCallbacks;
import br.tbm.github.api.network.rest.RestUser;
import br.tbm.github.api.database.tasks.SaveGithubUserTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class SearchByUsernameModel implements
        SearchByUsernameMVP.Model,
        TasksCallbacks.SaveGithubUserTaskCallback {

    private SearchByUsernameMVP.Presenter mPresenter;

    public SearchByUsernameModel(SearchByUsernameMVP.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void searchInServer(String profileName) {
        RestUser service = GithubApplication.getRetrofitInstance().create(RestUser.class);
        Call<Profile> responseCall = service.getProfile(profileName);
        responseCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                searchProfileResponseSuccess(response);
            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                mPresenter.displayAlertDialog(t.getMessage());
            }
        });
    }

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
                mPresenter.displayAlertDialog(R.string.search_activity_user_not_found);
            }
        } else {
            mPresenter.networkIssue(response.raw().code());
        }
    }

    // ################
    // CALLBACK DA TASK
    // ################

    @Override
    public void saveGithubUserTaskSuccess(Profile profile) {
        mPresenter.success(profile);
    }

    @Override
    public void saveGithubUserTaskFailure() {
        mPresenter.displayAlertDialog(R.string.generic_database_issue);
    }


}
