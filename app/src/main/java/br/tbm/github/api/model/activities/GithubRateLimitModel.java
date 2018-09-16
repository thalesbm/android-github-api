package br.tbm.github.api.model.activities;

import android.support.annotation.NonNull;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.interfaces.activities.GithubRateLimitMVP;
import br.tbm.github.api.network.entities.ResourcesResponse;
import br.tbm.github.api.network.rest.RestGithub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class GithubRateLimitModel implements GithubRateLimitMVP.Model {

    private GithubRateLimitMVP.Presenter mPresenter;

    public GithubRateLimitModel(GithubRateLimitMVP.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void searchInServer() {
        RestGithub service = GithubApplication.getRetrofitInstance().create(RestGithub.class);
        Call<ResourcesResponse> responseCall = service.getRateLimit();
        responseCall.enqueue(new Callback<ResourcesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ResourcesResponse> call, @NonNull Response<ResourcesResponse> response) {
                if (response.isSuccessful()) {
                    mPresenter.success(response.body());
                } else {
                    mPresenter.networkIssue(response.raw().code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResourcesResponse> call, @NonNull Throwable t) {
                mPresenter.displayAlertDialog(t.getMessage());
            }
        });
    }
}
