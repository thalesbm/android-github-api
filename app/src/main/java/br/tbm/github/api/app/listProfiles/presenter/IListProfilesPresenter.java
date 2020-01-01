package br.tbm.github.api.app.listProfiles.presenter;

import java.util.List;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IListProfilesPresenter extends BasePresenterCallbacks<List<Profile>> {
    void getProfilesInDatabase();

    void removeItems(List<Profile> profiles);

    String checkNumberOfItemsHasBeenChecked(List<Profile> profiles);
}
