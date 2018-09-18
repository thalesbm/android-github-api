package br.tbm.github.api.interfaces;

import java.util.List;

import br.tbm.github.api.network.entities.EventCommitsResponse;

/**
 * Created by thalesbertolini on 18/09/2018
 **/
public interface EventDetailsMVP {

    interface View {
        void listEventsEmpty();

        void listEvents();
    }

    interface Presenter {
        void validateEventsList(List<EventCommitsResponse> events);
    }

    interface Model {

    }
}
