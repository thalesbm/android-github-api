package br.tbm.github.api.app.searchByUsername.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.tbm.github.api.R;
import br.tbm.github.api.app.profile.repository.model.Profile;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SearchByUsernamePresenterTest {

    @Mock
    SearchByUsernameMVP.View mView;

    @Mock
    SearchByUsernameMVP.Model mModel;

    private SearchByUsernamePresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new SearchByUsernamePresenter(mView, mModel);
    }

    @Test
    public void searchUsernameInServer_Test() {
        mPresenter.searchUsernameInServer("thalesbm92");
        verify(mModel, atLeastOnce()).searchUsernameInServer("thalesbm92");
    }

    @Test
    public void validateFields_EmptyField_Test() {
        mPresenter.validateFields("");
        verify(mView, atLeastOnce()).validateNotPassed(R.string.search_activity_profile_validation);
    }

    @Test
    public void validateFields_Test() {
        mPresenter.validateFields("thalesbm");
        verify(mView, atLeastOnce()).validatePassed();
        verify(mView, atLeastOnce()).hideKeyboard();
    }

    @Test
    public void success_Test() {
        Profile profile = mock(Profile.class);
        mPresenter.success(profile);
        verify(mView, atLeastOnce()).success(profile);
    }

    @Test
    public void networkIssue_Test() {
        mPresenter.networkIssue(500);
        verify(mView, atLeastOnce()).networkIssue(500, false);
    }

    @Test
    public void displayAlertDialog_Test() {
        mPresenter.displayAlertDialog(500);
        verify(mView, atLeastOnce()).displayAlertDialog(500, false);
    }
}