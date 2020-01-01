package br.tbm.github.api.app.listProfiles.repository;

import java.util.List;

import br.tbm.github.api.app.listProfiles.presenter.IListProfilesPresenter;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IListProfilesRepository {
    void listProfilesInDatabase();

    void removeProfilesFromDatabase(List<Profile> profiles);

    void setCallback(IListProfilesPresenter presenter);
}
