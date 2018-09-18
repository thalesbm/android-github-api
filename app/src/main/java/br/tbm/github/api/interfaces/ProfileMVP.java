package br.tbm.github.api.interfaces;

import java.util.ArrayList;

import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ProfileMVP {

    interface View extends BaseViewCallbacks<RepositoriesResponse> {
        void repositoriesListEmpty();
        void displayRepositories(ArrayList<RepositoriesResponse> repositories);
        void downloadProfileImage(String avatarUrl);
        void updateToolbarTitle(String name);
    }

    interface Presenter extends BasePresenterCallbacks<RepositoriesResponse> {
        void searchPublicRepositories(String profileName);
        void updateScreen(Profile mProfile);
    }

    interface Model {
        void searchInServer(String profileName);
        void setCallback(ProfileMVP.Presenter presenter);
    }
}
