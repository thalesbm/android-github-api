package br.tbm.github.api.app.commitDetails.presenter;

import br.tbm.github.api.R;
import br.tbm.github.api.app.commitDetails.repository.ICommitDetailsRepository;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.app.commitDetails.view.ICommitDetailsView;
import br.tbm.github.api.commons.presenter.BasePresenter;
import br.tbm.github.api.commons.utils.DateUtils;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class CommitsDetailsPresenter extends BasePresenter<CommitsResponse> implements
        ICommitDetailsPresenter {

    private ICommitDetailsView mView;
    private ICommitDetailsRepository mModel;

    public CommitsDetailsPresenter(ICommitDetailsView view, ICommitDetailsRepository model) {
        super();
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void needsToCloseCurrentActivity() {
        closeActivity = true;
    }

    @Override
    public void searchCommitDetailsInServer(String username, String repositoryName, String sha) {
        mView.updateProgressDialog(R.string.loading);
        mModel.searchCommitDetailsInServer(username, repositoryName, sha, this);
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

            if (response.getOwnerResponse().getAvatarUrl() != null && !response.getOwnerResponse().getAvatarUrl().equals("")) {
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
