package br.tbm.github.api.app.branch.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.app.branch.BranchMVP;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BranchPresenterTest {

    @Mock
    BranchMVP.View mView;

    @Mock
    BranchMVP.Model mModel;

    private BranchPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new BranchPresenter(mView, mModel);
    }

    @Test
    public void searchBranchesInServer_Test() {
        mPresenter.searchBranchesInServer("thalesbm92", "android-github-api");
        verify(mView, atLeastOnce()).updateProgressDialog(R.string.loading);
        verify(mModel, atLeastOnce()).searchBranchesInServer("thalesbm92", "android-github-api");
    }

    @Test
    public void success_EmptyList_Test() {
        mPresenter.success(new ArrayList<>());
        verify(mView, atLeastOnce()).branchesListEmpty();
    }

    @Test
    public void success_Test() {
        ArrayList<BranchesTagsResponse> arrayList = new ArrayList<>();
        arrayList.add(new BranchesTagsResponse());
        mPresenter.success(arrayList);
        verify(mView, atLeastOnce()).branchesList(arrayList);
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