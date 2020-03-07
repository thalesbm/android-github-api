package br.tbm.github.api.app.searchByUsername.repository;

import br.tbm.github.api.app.searchByUsername.presenter.ISearchByUsernamePresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ISearchByUsernameRepository {
    void searchUsernameInServer(String profileName);
    void setCallback(ISearchByUsernamePresenter presenter);
}
