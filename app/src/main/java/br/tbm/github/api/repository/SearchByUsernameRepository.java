package br.tbm.github.api.repository;

import android.content.Context;
import android.support.annotation.NonNull;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.R;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.interfaces.SearchByUsernameMVP;
import br.tbm.github.api.database.tasks.TasksCallbacks;
import br.tbm.github.api.network.rest.RestUser;
import br.tbm.github.api.database.tasks.SaveGithubUserTask;
import br.tbm.github.api.utils.AppUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class SearchByUsernameRepository implements
        SearchByUsernameMVP.Model,
        TasksCallbacks.SaveGithubUserTaskCallback {

    private SearchByUsernameMVP.Presenter mPresenter;

    private Context mContext;

    public SearchByUsernameRepository(Context context){
        this.mContext = context;
    }

    @Override
    public void searchUsernameInServer(String profileName) {
        if (AppUtils.isOnline(mContext)) {
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
        } else {
            mPresenter.displayAlertDialog(R.string.generic_internet_issue);
        }
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

    /**
     * Metodo responsavel por adicionar a instancia do presenter no repository
     *
     * @param presenter SearchByUsernameMVP.Presenter
     */
    @Override
    public void setCallback(SearchByUsernameMVP.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
