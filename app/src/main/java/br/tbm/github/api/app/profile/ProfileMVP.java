package br.tbm.github.api.app.profile;

import java.util.ArrayList;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.app.profile.repository.entity.RepositoriesResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

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
        void searchPublicRepositoriesInServer(String profileName);
        void updateScreen(Profile mProfile);
    }

    interface Model {
        void searchPublicRepositoriesInServer(String profileName);
        void setCallback(ProfileMVP.Presenter presenter);
    }
}
