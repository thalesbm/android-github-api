package br.tbm.github.api.app.event;

import java.util.ArrayList;

import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface EventMVP {

    interface View extends BaseViewCallbacks<EventsResponse> {
        void listEventsEmpty();
        void listEvents(ArrayList<EventsResponse> events);
    }

    interface Presenter extends BasePresenterCallbacks<EventsResponse> {
        void searchEventsInServer(String profileName, String repositoryName);
    }

    interface Model {
        void searchEventsInServer(String profileName, String repositoryName);
        void setCallback(EventMVP.Presenter presenter);
    }
}
