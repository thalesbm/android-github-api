package br.tbm.github.api.presenter.activities;

import br.tbm.github.api.interfaces.activities.SearchByUsernameMVP;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.model.activities.SearchByUsernameModel;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class SearchByUsernamePresenter extends BasePresenter<Profile> implements
        SearchByUsernameMVP.Presenter {

    private SearchByUsernameMVP.View mView;
    private SearchByUsernameMVP.Model mModel;

    public SearchByUsernamePresenter(SearchByUsernameMVP.View view) {
        this.mView = view;
        this.mModel = new SearchByUsernameModel(this);

        closeActivity = false;
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

    // ####################
    // CALLBACK DO MODEL
    // ####################
    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }

    @Override
    public void networkIssue(int code) {
        super.networkIssue(code);
        mView.networkIssue(code, closeActivity);
    }

    @Override
    public void success(Profile profile) {
        super.success(profile);
        mView.success(profile);
    }
}
