package br.tbm.github.api.presenter;

import br.tbm.github.api.R;
import br.tbm.github.api.network.entities.CommitsResponse;
import br.tbm.github.api.interfaces.CommitDetailsMVP;
import br.tbm.github.api.utils.DateUtils;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class CommitsDetailsPresenter extends BasePresenter<CommitsResponse> implements
        CommitDetailsMVP.Presenter {

    private CommitDetailsMVP.View mView;
    private CommitDetailsMVP.Model mModel;

    public CommitsDetailsPresenter(CommitDetailsMVP.View view, CommitDetailsMVP.Model model) {
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
    public void searchCommitDetailsInServer(String username, String repositoryName, String sha) {
        mView.updateProgressDialog(R.string.loading);
        mModel.searchCommitDetailsInServer(username, repositoryName, sha);
    }

    // ######################
    // CALLBACK DO REPOSITORY
    // ######################

    @Override
    public void success(CommitsResponse response) {
        super.success(response);

        if (response.getCommitFilesResponse() != null) {
            mView.setCommitterName(response.getOwnerResponse().getLogin());
            mView.setCommitDescription(response.getCommitDetailsResponse().getMessage());
            mView.setCommitterDate(DateUtils.formatDate(response.getCommitDetailsResponse().getCommitterResponse().getDate()));

            if (!response.getOwnerResponse().getAvatarUrl().equals("")) {
                mView.downloadProfileImage(response.getOwnerResponse().getAvatarUrl());
            }

            if (response.getCommitFilesResponse().isEmpty()) {
                mView.listCommitsEmpty();
            } else {
                mView.listCommits(response.getCommitFilesResponse());
            }

        } else {
            mView.displayAlertDialog(R.string.generic_connection_issue, true);
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
