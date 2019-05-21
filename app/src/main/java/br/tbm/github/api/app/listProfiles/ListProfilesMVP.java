package br.tbm.github.api.app.listProfiles;

import java.util.List;

import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;
import br.tbm.github.api.app.profile.repository.model.Profile;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ListProfilesMVP {

    interface View extends BaseViewCallbacks<List<Profile>> {
        void listProfilesSuccess(List<Profile> profiles);
        void listProfilesEmpty();
    }

    interface Presenter extends BasePresenterCallbacks<List<Profile>> {
        void getProfilesInDatabase();
        void removeItems(List<Profile> profiles);

        String checkNumberOfItemsHasBeenChecked(List<Profile> profiles);
    }

    interface Model {
        void listProfilesInDatabase();
        void removeProfilesFromDatabase(List<Profile> profiles);
        void setCallback(ListProfilesMVP.Presenter presenter);
    }
}
