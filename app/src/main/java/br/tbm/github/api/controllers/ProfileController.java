package br.tbm.github.api.controllers;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.entities.RepositoriesResponse;
import br.tbm.github.api.interfaces.ControllerCallbacks;
import br.tbm.github.api.rest.RestUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class ProfileController {

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    private final boolean closeActivity = true;

    private ControllerCallbacks<RepositoriesResponse> mCallback;

    public ProfileController(ControllerCallbacks<RepositoriesResponse> callback) {
        this.mCallback = callback;
    }

    /**
     * Metodo para pesquisar um perfil pelo nome
     *
     * @param profileName String
     */
    public void search(String profileName) {
        RestUser service = GithubApplication.getRetrofitInstance().create(RestUser.class);
        Call<ArrayList<RepositoriesResponse>> responseCall = service.listRepositories(profileName);
        responseCall.enqueue(new Callback<ArrayList<RepositoriesResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<RepositoriesResponse>> call, @NonNull Response<ArrayList<RepositoriesResponse>> response) {

                if (response.isSuccessful()) {
                    mCallback.success(response.body());
                } else {
                    mCallback.networkIssue(response.raw().code(), closeActivity);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<RepositoriesResponse>> call, @NonNull Throwable t) {
                mCallback.displayAlertDialog(t.getMessage(), closeActivity);
            }
        });
    }
}
