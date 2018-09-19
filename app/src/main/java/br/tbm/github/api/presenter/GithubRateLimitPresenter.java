package br.tbm.github.api.presenter;

import br.tbm.github.api.R;
import br.tbm.github.api.network.entities.ResourcesResponse;
import br.tbm.github.api.interfaces.GithubRateLimitMVP;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class GithubRateLimitPresenter extends BasePresenter<ResourcesResponse> implements
        GithubRateLimitMVP.Presenter {

    private GithubRateLimitMVP.View mView;
    private GithubRateLimitMVP.Model mModel;

    public GithubRateLimitPresenter(GithubRateLimitMVP.View view, GithubRateLimitMVP.Model model) {
        super();
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);
    }

    @Override
    void needsToCloseCurrentActivity() {
        closeActivity = true;
    }

    @Override
    public void searchRateLimitInServer() {
        mView.updateProgressDialog(R.string.loading);
        mModel.searchRateLimitInServer();
    }

    // ######################
    // CALLBACK DO REPOSITORY
    // ######################

    @Override
    public void success(ResourcesResponse response) {
        super.success(response);
        if (response.getRateLimitResponse() != null) {

            // verifica se o objeto core é null, caso nao seja preencha com as informacoes do servidor
            if (response.getRateLimitResponse().getCore() != null) {
                mView.setCoreLimit(R.string.github_rate_limit_activity_limit, response.getRateLimitResponse().getCore().getLimit());
                mView.setCoreRemaining(R.string.github_rate_limit_activity_remaining, response.getRateLimitResponse().getCore().getRemaining());
            } else {
                mView.hideCoreFields();
            }

            // verifica se o objeto searchEventsInServer é null, caso nao seja preencha com as informacoes do servidor
            if (response.getRateLimitResponse().getSearch() != null) {
                mView.setSearchLimit(R.string.github_rate_limit_activity_limit, response.getRateLimitResponse().getSearch().getLimit());
                mView.setSearchRemaining(R.string.github_rate_limit_activity_remaining, response.getRateLimitResponse().getSearch().getRemaining());
            } else {
                mView.hideSearchFields();
            }

            // verifica se o objeto graph é null, caso nao seja preencha com as informacoes do servidor
            if (response.getRateLimitResponse().getGraphql() != null) {
                mView.setGraphLimit(R.string.github_rate_limit_activity_limit, response.getRateLimitResponse().getGraphql().getLimit());
                mView.setGraphRemaining(R.string.github_rate_limit_activity_remaining, response.getRateLimitResponse().getGraphql().getRemaining());
            } else {
                mView.hideGraphFields();
            }
        }
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
