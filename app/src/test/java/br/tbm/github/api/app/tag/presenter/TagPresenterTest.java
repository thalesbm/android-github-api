package br.tbm.github.api.app.tag.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.tag.TagMVP;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TagPresenterTest {

    @Mock
    TagMVP.View mView;

    @Mock
    TagMVP.Model mModel;

    private TagPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new TagPresenter(mView, mModel);
    }

    @Test
    public void searchTagsInServer_Test() {
        mPresenter.searchTagsInServer("thalesbm92", "android-github-api");
        verify(mView, atLeastOnce()).updateProgressDialog(R.string.loading);
        verify(mModel, atLeastOnce()).searchTagsInServer("thalesbm92", "android-github-api");
    }

    @Test
    public void displayAlertDialog_Test() {
        mPresenter.displayAlertDialog(500);
        verify(mView, atLeastOnce()).displayAlertDialog(500, true);
    }

    @Test
    public void success_EmptyList_Test() {
        mPresenter.success(new ArrayList<>());
        verify(mView, atLeastOnce()).listTagsEmpty();
    }

    @Test
    public void success_Test() {
        ArrayList<BranchesTagsResponse> arrayList = new ArrayList<>();
        arrayList.add(new BranchesTagsResponse());
        mPresenter.success(arrayList);
        verify(mView, atLeastOnce()).listTags(arrayList);
    }

    @Test
    public void networkIssue_Test() {
        mPresenter.networkIssue(500);
        verify(mView, atLeastOnce()).networkIssue(500, true);
    }
}