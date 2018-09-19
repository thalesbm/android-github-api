package br.tbm.github.api.presenter;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.interfaces.ProfileMVP;
import br.tbm.github.api.network.entities.RepositoriesResponse;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class ProfilePresenter extends BasePresenter<RepositoriesResponse> implements
        ProfileMVP.Presenter {

    private ProfileMVP.View mView;
    private ProfileMVP.Model mModel;

    public ProfilePresenter(ProfileMVP.View view, ProfileMVP.Model model) {
        super();
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);
    }

    @Override
    void needsToCloseCurrentActivity() {
        closeActivity = true;
    }

    @Override
    public void searchPublicRepositoriesInServer(String profileName) {
        mView.updateProgressDialog(R.string.loading);
        mModel.searchPublicRepositoriesInServer(profileName);
    }

    @Override
    public void updateScreen(Profile profile) {
        mView.updateToolbarTitle(profile.getName());

        if (!profile.getAvatarUrl().equals("")) {
            mView.downloadProfileImage(profile.getAvatarUrl());
        }
    }

    // ######################
    // CALLBACK DO REPOSITORY
    // ######################

    @Override
    public void success(ArrayList<RepositoriesResponse> repositories) {
        super.success(repositories);

        if (repositories.isEmpty()) {
            mView.repositoriesListEmpty();
        } else {
            mView.displayRepositories(repositories);
        }
    }

    @Override
    public void networkIssue(int code) {
        super.networkIssue(code);
        mView.networkIssue(code, closeActivity);
    }

    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }
}
