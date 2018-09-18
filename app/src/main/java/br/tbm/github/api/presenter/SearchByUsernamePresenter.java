package br.tbm.github.api.presenter;

import br.tbm.github.api.R;
import br.tbm.github.api.interfaces.SearchByUsernameMVP;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class SearchByUsernamePresenter extends BasePresenter<Profile> implements
        SearchByUsernameMVP.Presenter {

    private SearchByUsernameMVP.View mView;
    private SearchByUsernameMVP.Model mModel;

    public SearchByUsernamePresenter(SearchByUsernameMVP.View view, SearchByUsernameMVP.Model model) {
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);

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

    @Override
    public void validateFields(String field) {
        if (field.trim().isEmpty()) {
            mView.validateNotPassed(R.string.search_activity_profile_validation);
        } else {
            mView.validatePassed();
            if (mView.checkConnection()) {
                mView.hideKeyboard();

                this.search(field);
            } else {
                mView.displayAlertDialog(R.string.generic_internet_issue, true);
            }
        }
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
