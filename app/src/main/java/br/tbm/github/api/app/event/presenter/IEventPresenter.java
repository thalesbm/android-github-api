package br.tbm.github.api.app.event.presenter;

import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IEventPresenter extends BasePresenterCallbacks<EventsResponse> {
    void searchEventsInServer(String profileName, String repositoryName);
}
