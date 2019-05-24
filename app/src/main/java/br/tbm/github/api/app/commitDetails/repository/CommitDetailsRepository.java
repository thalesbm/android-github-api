package br.tbm.github.api.app.commitDetails.repository;

import android.support.annotation.NonNull;

import br.tbm.github.api.shared.GithubApplication;
import br.tbm.github.api.app.commitDetails.CommitDetailsMVP;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.shared.network.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class CommitDetailsRepository implements CommitDetailsMVP.Model {

    private CommitDetailsMVP.Presenter mPresenter;
    private Retrofit mRetrofit;

    /**
     * Metodo para pesquisar todos os commits de um especifico repositorio
     *
     * @param username       String
     * @param repositoryName String
     * @param sha            String
     */
    @Override
    public void searchCommitDetailsInServer(String username, String repositoryName, String sha) {
        Call<CommitsResponse> responseCall = mRetrofit.create(RestRepository.class).listCommits(username, repositoryName, sha);
        responseCall.enqueue(new Callback<CommitsResponse>() {
            @Override
            public void onResponse(@NonNull Call<CommitsResponse> call, @NonNull Response<CommitsResponse> response) {

                if (response.isSuccessful()) {
                    mPresenter.success(response.body());
                } else {
                    mPresenter.networkIssue(response.raw().code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<CommitsResponse> call, @NonNull Throwable t) {
                mPresenter.displayAlertDialog(t.getMessage());
            }
        });
    }

    /**
     * Metodo responsavel por adicionar a instancia do presenter no repository
     *
     * @param presenter CommitDetailsMVP.Presenter
     */
    @Override
    public void setCallback(CommitDetailsMVP.Presenter presenter, Retrofit retrofit) {
        this.mPresenter = presenter;
        this.mRetrofit = retrofit;
    }
}
