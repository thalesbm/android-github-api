package br.tbm.github.api.interfaces;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;
import br.tbm.github.api.database.data.Profile;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface SearchByUsernameMVP {

    interface View extends BaseViewCallbacks<Profile> {
        void validateNotPassed(int message);
        void validatePassed();
    }

    interface Presenter extends BasePresenterCallbacks<Profile> {
        void searchUsernameInServer(String profileName);
        void validateFields(String field);
    }

    interface Model {
        void searchUsernameInServer(String profileName, SearchByUsernameMVP.Presenter listener);
        void setCallback(SearchByUsernameMVP.Presenter presenter);
    }
}
