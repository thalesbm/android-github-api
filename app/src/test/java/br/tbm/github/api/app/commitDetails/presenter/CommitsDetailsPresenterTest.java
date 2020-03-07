package br.tbm.github.api.app.commitDetails.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.tbm.github.api.R;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitFilesResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.commons.GithubApiUtils;
import br.tbm.github.api.commons.utils.DateUtils;

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
        CommitsResponse response = GithubApiUtils.getCommitsResponse();
        mPresenter.success(response);
        verify(mView, atLeastOnce()).setCommitterName(response.getOwnerResponse().getLogin());
        verify(mView, atLeastOnce()).setCommitDescription(response.getCommitDetailsResponse().getMessage());
        verify(mView, atLeastOnce()).setCommitterDate(DateUtils.formatDate(response.getCommitDetailsResponse().getCommitterResponse().getDate()));
    }

    @Test
    public void success_EmptyList_Test() {
        CommitsResponse response = GithubApiUtils.getCommitsResponse();
        mPresenter.success(response);
        verify(mView, atLeastOnce()).listCommitsEmpty();
    }

    @Test
    public void success_setAvatar_Test() {
        CommitsResponse response = GithubApiUtils.getCommitsResponse();
        response.getOwnerResponse().setAvatarUrl("url");
        mPresenter.success(response);
        verify(mView, atLeastOnce()).downloadProfileImage(response.getOwnerResponse().getAvatarUrl());
    }

    @Test
    public void success_Test() {
        CommitsResponse response = GithubApiUtils.getCommitsResponse();
        response.getCommitFilesResponse().add(new CommitFilesResponse());
        mPresenter.success(response);
        verify(mView, atLeastOnce()).listCommits(response.getCommitFilesResponse());
    }

    @Test
    public void success_NullObject_Test() {
        CommitsResponse response = GithubApiUtils.getCommitsResponse();
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


}