package br.tbm.github.api.app.listProfiles.repository;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.listProfiles.presenter.IListProfilesPresenter;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.app.profile.repository.tasks.ListGithubUsersTask;
import br.tbm.github.api.app.profile.repository.tasks.RemoveUsersTask;
import br.tbm.github.api.app.profile.repository.tasks.TasksCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class ListProfilesRepository implements IListProfilesRepository,
        TasksCallbacks.RemoveUsersTaskCallback,
        TasksCallbacks.ListGithubUserTaskCallback {

    private IListProfilesPresenter mPresenter;

    @Override
    public void listProfilesInDatabase() {
        new ListGithubUsersTask(this).execute();
    }

    @Override
    public void removeProfilesFromDatabase(List<Profile> profiles) {
        new RemoveUsersTask(this, profiles).execute();
    }

    // ################
    // CALLBACK DA TASK
    // ################

    @Override
    public void listGithubUserTaskSuccess(List<Profile> profiles) {
        mPresenter.success(profiles);
    }

    @Override
    public void listGithubUserTaskFailure() {
        mPresenter.displayAlertDialog(R.string.generic_database_issue);
    }

    @Override
    public void removeUserTaskSuccess(List<Profile> profiles) {
        mPresenter.success(profiles);
    }

    @Override
    public void removeUserTaskFailure() {
        mPresenter.displayAlertDialog(R.string.generic_database_issue);
    }

    /**
     * Metodo responsavel por adicionar a instancia do presenter no repository
     *
     * @param presenter ListProfilesMVP.Presenter
     */
    @Override
    public void setCallback(IListProfilesPresenter presenter) {
        this.mPresenter = presenter;
    }
}
