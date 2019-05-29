package br.tbm.github.api.app.branch.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.BranchMVP;
import br.tbm.github.api.app.branch.presenter.BranchPresenter;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.commitDetails.CommitDetailsMVP;
import br.tbm.github.api.app.commitDetails.presenter.CommitsDetailsPresenter;
import br.tbm.github.api.shared.repository.BaseTestsRepository;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BranchRepositoryTest extends BaseTestsRepository {

    @Mock
    BranchMVP.View mView;

    @Mock
    BranchMVP.Model mModel;

    private BranchMVP.Presenter mPresenter;

    @Before
    public void setUp() {
        super.setUp();
        mPresenter = new BranchPresenter(mView, mModel);
    }

    @Test
    public void searchBranchesInServer_sesuccess_Test() {
        ArrayList<BranchesTagsResponse> response = new ArrayList<BranchesTagsResponse>();
        doAnswer(invocation -> {
            BranchMVP.Presenter callback = invocation.getArgument(2);
            callback.success(response);
            return null;

        }).when(mModel).searchBranchesInServer(username, repository, mPresenter);

        mPresenter.searchBranchesInServer(username, repository);

        verify(mView, atLeastOnce()).branchesListEmpty();
    }

    @Test
    public void searchBranchesInServer_networkIssue_Test() {
        doAnswer(invocation -> {
            BranchMVP.Presenter callback = invocation.getArgument(2);
            callback.networkIssue(400);
            return null;

        }).when(mModel).searchBranchesInServer(username, repository, mPresenter);

        mPresenter.searchBranchesInServer(username, repository);

        verify(mView, atLeastOnce()).networkIssue(400, true);
    }

    @Test
    public void searchBranchesInServer_displayAlertDialog_Test() {
        doAnswer(invocation -> {
            BranchMVP.Presenter callback = invocation.getArgument(2);
            callback.displayAlertDialog(400);
            return null;

        }).when(mModel).searchBranchesInServer(username, repository, mPresenter);

        mPresenter.searchBranchesInServer(username, repository);

        verify(mView, atLeastOnce()).displayAlertDialog(400, true);
    }
}