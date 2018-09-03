package br.tbm.github.api.controllers;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.entities.BranchesTagsResponse;
import br.tbm.github.api.interfaces.ControllerCallbacks;
import br.tbm.github.api.rest.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class BranchController {

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    private final boolean closeActivity = true;

    private ControllerCallbacks<BranchesTagsResponse> mCallback;

    public BranchController(ControllerCallbacks<BranchesTagsResponse> callback) {
        this.mCallback = callback;
    }

    /**
     * Metodo para pesquisar as branches de um determinado perfil
     *
     * @param profileName String
     */
    public void search(String profileName, String repositoryName) {
        RestRepository service = GithubApplication.getRetrofitInstance().create(RestRepository.class);
        Call<ArrayList<BranchesTagsResponse>> responseCall = service.listBranches(profileName, repositoryName);
        responseCall.enqueue(new Callback<ArrayList<BranchesTagsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Response<ArrayList<BranchesTagsResponse>> response) {
                if (response.isSuccessful()) {
                    mCallback.success(response.body());
                } else {
                    mCallback.networkIssue(response.raw().code(), closeActivity);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Throwable t) {
                mCallback.displayAlertDialog(t.getMessage(), closeActivity);
            }
        });
    }
}
