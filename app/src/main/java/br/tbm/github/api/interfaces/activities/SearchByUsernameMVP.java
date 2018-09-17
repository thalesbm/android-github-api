package br.tbm.github.api.interfaces.activities;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;
import br.tbm.github.api.database.data.Profile;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface SearchByUsernameMVP {

    interface View extends BaseViewCallbacks<Profile> {

    }

    interface Presenter extends BasePresenterCallbacks<Profile> {
        void search(String profileName);

    }

    interface Model {
        void searchInServer(String profileName);

        void setCallback(SearchByUsernameMVP.Presenter presenter);
    }
}
