package br.tbm.github.api.interfaces.fragments;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.network.entities.EventsResponse;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface EventMVP {

    interface View extends BaseViewCallbacks<EventsResponse> {
    }

    interface Presenter extends BasePresenterCallbacks<EventsResponse> {
        void search(String profileName, String repositoryName);
    }

    interface Model {
        void searchInServer(String profileName, String repositoryName);
    }
}
