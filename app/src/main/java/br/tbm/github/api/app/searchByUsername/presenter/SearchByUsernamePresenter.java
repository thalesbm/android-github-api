package br.tbm.github.api.app.searchByUsername.presenter;

import br.tbm.github.api.R;
import br.tbm.github.api.app.searchByUsername.repository.ISearchByUsernameRepository;
import br.tbm.github.api.app.searchByUsername.view.ISearchByUsernameView;
import br.tbm.github.api.shared.presenter.BasePresenter;
import br.tbm.github.api.app.profile.repository.model.Profile;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class SearchByUsernamePresenter extends BasePresenter<Profile> implements
        ISearchByUsernamePresenter {

    private ISearchByUsernameView mView;
    private ISearchByUsernameRepository mModel;

    public SearchByUsernamePresenter(ISearchByUsernameView view, ISearchByUsernameRepository model) {
        super();
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);
    }

    @Override
    public void needsToCloseCurrentActivity() {
        closeActivity = false;
    }

    @Override
    public void searchUsernameInServer(String profileName) {
        mModel.searchUsernameInServer(profileName);
    }

    @Override
    public void validateFields(String field) {
        if (field.trim().isEmpty()) {
            mView.validateNotPassed(R.string.search_activity_profile_validation);
        } else {
            mView.validatePassed();
            mView.hideKeyboard();

            this.searchUsernameInServer(field);
        }
    }

    // ######################
    // CALLBACK DO REPOSITORY
    // ######################

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
