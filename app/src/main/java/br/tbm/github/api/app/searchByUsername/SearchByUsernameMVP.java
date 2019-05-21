package br.tbm.github.api.app.searchByUsername;

import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;
import br.tbm.github.api.app.profile.repository.model.Profile;

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
        void searchUsernameInServer(String profileName);
        void setCallback(SearchByUsernameMVP.Presenter presenter);
    }
}
