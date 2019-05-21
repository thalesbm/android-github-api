package br.tbm.github.api.app.branch.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.shared.GithubApplication;
import br.tbm.github.api.app.branch.BranchMVP;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.shared.network.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class BranchRepository implements BranchMVP.Model {

    private BranchMVP.Presenter mPresenter;

    @Override
    public void searchBranchesInServer(String profileName, String repositoryName) {
        RestRepository service = GithubApplication.getRetrofitInstance().create(RestRepository.class);
        Call<ArrayList<BranchesTagsResponse>> responseCall = service.listBranches(profileName, repositoryName);
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
     * @param presenter BranchMVP.Presenter
     */
    @Override
    public void setCallback(BranchMVP.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
