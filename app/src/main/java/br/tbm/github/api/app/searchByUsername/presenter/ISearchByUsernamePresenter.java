package br.tbm.github.api.app.searchByUsername.presenter;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ISearchByUsernamePresenter extends BasePresenterCallbacks<Profile> {
    void searchUsernameInServer(String profileName);
    void validateFields(String field);
}
