package br.tbm.github.api.app.listProfiles.repository;

import java.util.List;

import br.tbm.github.api.app.listProfiles.presenter.IListProfilesPresenter;
import br.tbm.github.api.app.profile.repository.model.Profile;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IListProfilesRepository {
    void listProfilesInDatabase();

    void removeProfilesFromDatabase(List<Profile> profiles);

    void setCallback(IListProfilesPresenter presenter);
}
