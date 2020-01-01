package br.tbm.github.api.app.searchByUsername.repository;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.app.searchByUsername.presenter.ISearchByUsernamePresenter;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ISearchByUsernameRepository {
    void searchUsernameInServer(String profileName);
    void setCallback(ISearchByUsernamePresenter presenter);
}
