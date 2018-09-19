package br.tbm.github.api.interfaces;

import java.util.ArrayList;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.network.entities.EventsResponse;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;

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
