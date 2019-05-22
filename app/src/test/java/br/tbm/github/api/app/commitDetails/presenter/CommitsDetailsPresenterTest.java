package br.tbm.github.api.app.commitDetails.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.tbm.github.api.R;
import br.tbm.github.api.app.commitDetails.CommitDetailsMVP;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
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
        verify(mModel, atLeastOnce()).searchCommitDetailsInServer("thalesbm92", "android-github-api", "sha-256");
    }

    @Test
    public void success_Test() {
        // TODO: CRIAR ESSE TESTE NO FUTURO PROXIMO
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