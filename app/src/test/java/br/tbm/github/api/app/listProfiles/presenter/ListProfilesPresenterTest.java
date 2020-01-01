package br.tbm.github.api.app.listProfiles.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.profile.repository.model.Profile;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListProfilesPresenterTest {

    @Mock
    ListProfilesMVP.View mView;

    @Mock
    ListProfilesMVP.Model mModel;

    private ListProfilesPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new ListProfilesPresenter(mView, mModel);
    }

    @Test
    public void getProfilesInDatabase_Test() {
        mPresenter.getProfilesInDatabase();
        verify(mView, atLeastOnce()).updateProgressDialog(R.string.loading);
        verify(mModel, atLeastOnce()).listProfilesInDatabase();
    }

    @Test
    public void removeItems_Test() {
        List<Profile> profiles = createProfileList();
        mPresenter.removeItems(profiles);
        verify(mModel, atLeastOnce()).removeProfilesFromDatabase(profiles);
    }

    @Test
    public void checkNumberOfItemsHasBeenChecked_Test() {
        String qtd = mPresenter.checkNumberOfItemsHasBeenChecked(createProfileList());
        assertEquals(qtd, "10");
    }

    @Test
    public void displayAlertDialog_Test() {
        mPresenter.displayAlertDialog(500);
        verify(mView, atLeastOnce()).displayAlertDialog(500, false);
    }

    @Test
    public void success_EmptyList_Test() {
        mPresenter.success(new ArrayList<Profile>());
        verify(mView, atLeastOnce()).listProfilesEmpty();
    }

    @Test
    public void success_Test() {
        ArrayList<Profile> arrayList = new ArrayList<>();
        arrayList.add(new Profile());
        mPresenter.success(arrayList);
        verify(mView, atLeastOnce()).listProfilesSuccess(arrayList);
    }

    private List<Profile> createProfileList() {
        List<Profile> profiles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Profile profile = new Profile();
            profile.setHasSelected(true);
            profiles.add(profile);
        }
        return profiles;
    }
}