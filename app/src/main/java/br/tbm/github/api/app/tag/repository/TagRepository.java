package br.tbm.github.api.app.tag.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.tag.presenter.ITagPresenter;
import br.tbm.github.api.commons.GithubApplication;
import br.tbm.github.api.commons.network.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class TagRepository implements ITagRepository {

    private ITagPresenter mPresenter;

    @Override
    public void searchTagsInServer(String profileName, String repositoryName) {
        RestRepository service = GithubApplication.getRetrofitInstance().create(RestRepository.class);
        Call<ArrayList<BranchesTagsResponse>> responseCall = service.listTags(profileName, repositoryName);
        responseCall.enqueue(new Callback<ArrayList<BranchesTagsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Response<ArrayList<BranchesTagsResponse>> response) {
                if (response.isSuccessful()) {
                    mPresenter.success(response.body());
                } else {
                    mPresenter.networkIssue(response.raw().code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Throwable t) {
                mPresenter.displayAlertDialog(t.getMessage());
            }
        });
    }

    /**
     * Metodo responsavel por adicionar a instancia do presenter no repository
     *
     * @param presenter TagMVP.Presenter
     */
    @Override
    public void setCallback(ITagPresenter presenter) {
        this.mPresenter = presenter;
    }
}
