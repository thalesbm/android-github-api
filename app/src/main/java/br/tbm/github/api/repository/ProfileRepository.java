package br.tbm.github.api.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.interfaces.ProfileMVP;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import br.tbm.github.api.network.rest.RestUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class ProfileRepository implements ProfileMVP.Model {

    private ProfileMVP.Presenter mPresenter;

    /**
     * Metodo para pesquisar um perfil pelo nome
     *
     * @param profileName String
     */
    @Override
    public void searchInServer(String profileName) {
        RestUser service = GithubApplication.getRetrofitInstance().create(RestUser.class);
        Call<ArrayList<RepositoriesResponse>> responseCall = service.listRepositories(profileName);
        responseCall.enqueue(new Callback<ArrayList<RepositoriesResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<RepositoriesResponse>> call, @NonNull Response<ArrayList<RepositoriesResponse>> response) {

                if (response.isSuccessful()) {
                    mPresenter.success(response.body());
                } else {
                    mPresenter.networkIssue(response.raw().code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<RepositoriesResponse>> call, @NonNull Throwable t) {
                mPresenter.displayAlertDialog(t.getMessage());
            }
        });
    }

    /**
     * Metodo responsavel por adicionar a instancia do presenter no repository
     *
     * @param presenter ProfileMVP.Presenter
     */
    @Override
    public void setCallback(ProfileMVP.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
