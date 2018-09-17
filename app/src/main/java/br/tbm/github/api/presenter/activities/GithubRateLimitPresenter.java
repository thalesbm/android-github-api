package br.tbm.github.api.presenter.activities;

import br.tbm.github.api.repository.activities.GithubRateLimitRepository;
import br.tbm.github.api.network.entities.ResourcesResponse;
import br.tbm.github.api.interfaces.activities.GithubRateLimitMVP;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class GithubRateLimitPresenter extends BasePresenter<ResourcesResponse> implements
        GithubRateLimitMVP.Presenter {

    private GithubRateLimitMVP.View mView;
    private GithubRateLimitMVP.Model mModel;

    public GithubRateLimitPresenter(GithubRateLimitMVP.View view, GithubRateLimitMVP.Model model) {
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);

        closeActivity = true;
    }

    /**
     * Metodo para verificar quantas requisicoes ainda posso fazer
     */
    @Override
    public void search() {
        mModel.searchInServer();
    }

    // ######################
    // CALLBACK DO MODEL
    // ######################

    @Override
    public void success(ResourcesResponse response) {
        super.success(response);
        mView.success(response);
    }

    @Override
    public void networkIssue(int code) {
        super.networkIssue(code);
        mView.networkIssue(code, closeActivity);
    }

    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }
}
