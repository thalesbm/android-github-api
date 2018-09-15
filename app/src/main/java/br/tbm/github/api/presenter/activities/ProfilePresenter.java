package br.tbm.github.api.presenter.activities;

import java.util.ArrayList;

import br.tbm.github.api.model.activities.ProfileModel;
import br.tbm.github.api.interfaces.ProfileMVP;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class ProfilePresenter extends BasePresenter<RepositoriesResponse> implements
        ProfileMVP.Presenter {

    private ProfileMVP.View mView;
    private ProfileMVP.Model mModel;

    public ProfilePresenter(ProfileMVP.View view) {
        this.mView = view;
        this.mModel = new ProfileModel(this);

        closeActivity = true;
    }

    /**
     * Metodo para pesquisar um perfil pelo nome
     *
     * @param profileName String
     */
    @Override
    public void search(String profileName) {
        mModel.searchInServer(profileName);
    }

    // ######################
    // CALLBACK DO MODEL
    // ######################

    @Override
    public void success(ArrayList<RepositoriesResponse> repositories) {
        super.success(repositories);
        mView.success(repositories);
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
