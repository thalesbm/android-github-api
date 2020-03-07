package br.tbm.github.api.app.event.repository;

import br.tbm.github.api.app.event.presenter.IEventPresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IEventRepository {
    void searchEventsInServer(String profileName, String repositoryName);
    void setCallback(IEventPresenter presenter);
}
