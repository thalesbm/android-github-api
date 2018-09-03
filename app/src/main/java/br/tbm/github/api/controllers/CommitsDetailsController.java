package br.tbm.github.api.controllers;

import android.support.annotation.NonNull;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.entities.CommitsResponse;
import br.tbm.github.api.interfaces.ControllerCallbacks;
import br.tbm.github.api.rest.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class CommitsDetailsController {

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    private final boolean closeActivity = true;

    private ControllerCallbacks<CommitsResponse> mCallback;

    public CommitsDetailsController(ControllerCallbacks<CommitsResponse> callback) {
        this.mCallback = callback;
    }

    /**
     * Metodo para pesquisar todos os commits de um especifico repositorio
     *
     * @param username String
     * @param repositoryName String
     * @param sha String
     */
    public void search(String username, String repositoryName, String sha) {
        RestRepository service = GithubApplication.getRetrofitInstance().create(RestRepository.class);
        Call<CommitsResponse> responseCall = service.listCommits(username, repositoryName, sha);
        responseCall.enqueue(new Callback<CommitsResponse>() {
            @Override
            public void onResponse(@NonNull Call<CommitsResponse> call, @NonNull Response<CommitsResponse> response) {

                if (response.isSuccessful()) {
                    mCallback.success(response.body());
                } else {
                    mCallback.networkIssue(response.raw().code(), closeActivity);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CommitsResponse> call, @NonNull Throwable t) {
                mCallback.displayAlertDialog(t.getMessage(), closeActivity);
            }
        });
    }
}
