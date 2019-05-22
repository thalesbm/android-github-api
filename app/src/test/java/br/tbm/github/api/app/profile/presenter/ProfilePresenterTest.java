package br.tbm.github.api.app.profile.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.app.profile.ProfileMVP;
import br.tbm.github.api.app.profile.repository.entity.RepositoriesResponse;
import br.tbm.github.api.app.profile.repository.model.Profile;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProfilePresenterTest {

    @Mock
    ProfileMVP.View mView;

    @Mock
    ProfileMVP.Model mModel;

    private ProfilePresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new ProfilePresenter(mView, mModel);
    }

    @Test
    public void searchPublicRepositoriesInServer_Test() {
        mPresenter.searchPublicRepositoriesInServer("thalesbm92");
        verify(mView, atLeastOnce()).updateProgressDialog(R.string.loading);
        verify(mModel, atLeastOnce()).searchPublicRepositoriesInServer("thalesbm92");
    }

    @Test
    public void updateScreen_Test() {
        Profile profile = new Profile();
        profile.setName("Thales Marega");
        profile.setAvatarUrl("url");
        mPresenter.updateScreen(profile);
        verify(mView, atLeastOnce()).updateToolbarTitle(profile.getName());
        verify(mView, atLeastOnce()).downloadProfileImage(profile.getAvatarUrl());
    }

    @Test
    public void success_EmptyList_Test() {
        mPresenter.success(new ArrayList<>());
        verify(mView, atLeastOnce()).repositoriesListEmpty();
    }

    @Test
    public void success_Test() {
        ArrayList<RepositoriesResponse> arrayList = new ArrayList<>();
        arrayList.add(new RepositoriesResponse());
        mPresenter.success(arrayList);
        verify(mView, atLeastOnce()).displayRepositories(arrayList);
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