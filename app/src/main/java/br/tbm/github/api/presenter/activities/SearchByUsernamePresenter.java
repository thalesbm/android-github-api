package br.tbm.github.api.presenter.activities;

import android.support.annotation.NonNull;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.R;
import br.tbm.github.api.interfaces.SearchByUsernameMVP;
import br.tbm.github.api.interfaces.generic.TasksCallbacks;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.model.activities.SearchByUsernameModel;
import br.tbm.github.api.network.rest.RestUser;
import br.tbm.github.api.presenter.BasePresenter;
import br.tbm.github.api.tasks.SaveGithubUserTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
