package br.tbm.github.api.app.branch.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.presenter.IBranchPresenter;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.commons.network.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class BranchRepository implements IBranchRepository {

    private Retrofit mRetrofit;

    public BranchRepository(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public void searchBranchesInServer(String profileName, String repositoryName, IBranchPresenter callback) {
        Call<ArrayList<BranchesTagsResponse>> responseCall = mRetrofit.create(RestRepository.class).listBranches(profileName, repositoryName);
        responseCall.enqueue(new Callback<ArrayList<BranchesTagsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Response<ArrayList<BranchesTagsResponse>> response) {
                if (response.isSuccessful()) {
                    callback.success(response.body());
                } else {
                    callback.networkIssue(response.raw().code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Throwable t) {
                callback.displayAlertDialog(t.getMessage());
            }
        });
    }
}
