package br.tbm.github.api.controllers;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.entities.ResourcesResponse;
import br.tbm.github.api.interfaces.ControllerCallbacks;
import br.tbm.github.api.rest.RestGithub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class GithubRateLimitController {

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    private final boolean closeActivity = true;

    private ControllerCallbacks<ResourcesResponse> mCallback;

    public GithubRateLimitController(ControllerCallbacks<ResourcesResponse> callback) {
        this.mCallback = callback;
    }

    /**
     * Metodo para verificar quantas requisicoes ainda posso fazer
     */
    public void search() {
        RestGithub service = GithubApplication.getRetrofitInstance().create(RestGithub.class);
        Call<ResourcesResponse> responseCall = service.getRateLimit();
        responseCall.enqueue(new Callback<ResourcesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResourcesResponse> call, @NonNull Response<ResourcesResponse> response) {
                if (response.isSuccessful()) {
                    mCallback.success(response.body());
                } else {
                    mCallback.networkIssue(response.raw().code(), closeActivity);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResourcesResponse> call, @NonNull Throwable t) {
                mCallback.displayAlertDialog(t.getMessage(), closeActivity);
            }
        });
    }
}
