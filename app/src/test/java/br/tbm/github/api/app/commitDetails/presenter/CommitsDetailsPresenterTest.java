package br.tbm.github.api.app.commitDetails.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.commitDetails.CommitDetailsMVP;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitDetailsResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitFilesResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitterResponse;
import br.tbm.github.api.app.profile.repository.entity.OwnerResponse;
import br.tbm.github.api.shared.utils.DateUtils;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommitsDetailsPresenterTest {

    @Mock
    CommitDetailsMVP.View mView;

    @Mock
    CommitDetailsMVP.Model mModel;

    private CommitsDetailsPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new CommitsDetailsPresenter(mView, mModel);
    }

    @Test
    public void searchCommitDetailsInServer_Test() {
        mPresenter.searchCommitDetailsInServer("thalesbm92", "android-github-api", "sha-256");
        verify(mView, atLeastOnce()).updateProgressDialog(R.string.loading);
        verify(mModel, atLeastOnce()).searchCommitDetailsInServer("thalesbm92", "android-github-api", "sha-256", mPresenter);
    }

    @Test
    public void success_Input_Test() {
        CommitsResponse response = this.getCommitsResponse();
        mPresenter.success(response);
        verify(mView, atLeastOnce()).setCommitterName(response.getOwnerResponse().getLogin());
        verify(mView, atLeastOnce()).setCommitDescription(response.getCommitDetailsResponse().getMessage());
        verify(mView, atLeastOnce()).setCommitterDate(DateUtils.formatDate(response.getCommitDetailsResponse().getCommitterResponse().getDate()));
    }

    @Test
    public void success_EmptyList_Test() {
        CommitsResponse response = this.getCommitsResponse();
        mPresenter.success(response);
        verify(mView, atLeastOnce()).listCommitsEmpty();
    }

    @Test
    public void success_setAvatar_Test() {
        CommitsResponse response = this.getCommitsResponse();
        response.getOwnerResponse().setAvatarUrl("url");
        mPresenter.success(response);
        verify(mView, atLeastOnce()).downloadProfileImage(response.getOwnerResponse().getAvatarUrl());
    }

    @Test
    public void success_Test() {
        CommitsResponse response = this.getCommitsResponse();
        response.getCommitFilesResponse().add(new CommitFilesResponse());
        mPresenter.success(response);
        verify(mView, atLeastOnce()).listCommits(response.getCommitFilesResponse());
    }

    @Test
    public void success_NullObject_Test() {
        CommitsResponse response = this.getCommitsResponse();
        response.setCommitFilesResponse(null);
        mPresenter.success(response);
        verify(mView, atLeastOnce()).displayAlertDialog(R.string.generic_connection_issue, true);
    }

    @Test
    public void networkIssue_Test() {
        mPresenter.networkIssue(500);
        verify(mView, atLeastOnce()).networkIssue(500, true);
    }

    @Test
    public void displayAlertDialog_Test() {
        mPresenter.displayAlertDialog(500);
        verify(mView, atLeastOnce()).displayAlertDialog(500, true);
    }

    private CommitsResponse getCommitsResponse() {
        CommitsResponse response = new CommitsResponse();

        List<CommitFilesResponse> files = new ArrayList<>();

        OwnerResponse owner = new OwnerResponse();
        owner.setLogin("Thales Marega");

        CommitterResponse committer = new CommitterResponse();
        committer.setDate("2018-09-20T09:45:00"); // yyyy-MM-dd'T'HH:mm:ss

        CommitDetailsResponse details = new CommitDetailsResponse();
        details.setMessage("ADDED TESTS");
        details.setCommitterResponse(committer);

        response.setCommitDetailsResponse(details);
        response.setOwnerResponse(owner);
        response.setCommitFilesResponse(files);
        return response;
    }
}