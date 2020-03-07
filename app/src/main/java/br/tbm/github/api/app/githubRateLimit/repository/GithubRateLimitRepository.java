package br.tbm.github.api.app.githubRateLimit.repository;

import android.support.annotation.NonNull;

import br.tbm.github.api.app.githubRateLimit.presenter.IGithubRateLimitPresenter;
import br.tbm.github.api.app.githubRateLimit.repository.entity.ResourcesResponse;
import br.tbm.github.api.commons.GithubApplication;
import br.tbm.github.api.commons.network.RestGithub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class GithubRateLimitRepository implements IGithubRateLimitRepository {

    private IGithubRateLimitPresenter mPresenter;

    @Override
    public void searchRateLimitInServer() {
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

    /**
     * Metodo responsavel por adicionar a instancia do presenter no repository
     *
     * @param presenter GithubRateLimitMVP.Presenter
     */
    @Override
    public void setCallback(IGithubRateLimitPresenter presenter) {
        this.mPresenter = presenter;
    }
}
