package br.tbm.github.api.interfaces.activities;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ProfileMVP {

    interface View extends BaseViewCallbacks<RepositoriesResponse> {
    }

    interface Presenter extends BasePresenterCallbacks<RepositoriesResponse> {
        void search(String profileName);
    }

    interface Model {
        void searchInServer(String profileName);
    }
}
