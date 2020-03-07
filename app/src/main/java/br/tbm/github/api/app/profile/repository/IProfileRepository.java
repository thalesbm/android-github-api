package br.tbm.github.api.app.profile.repository;

import br.tbm.github.api.app.profile.presenter.IProfilePresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IProfileRepository {
    void searchPublicRepositoriesInServer(String profileName);
    void setCallback(IProfilePresenter presenter);
}
