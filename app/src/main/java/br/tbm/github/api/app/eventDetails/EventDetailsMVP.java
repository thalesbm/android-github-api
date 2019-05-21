package br.tbm.github.api.app.eventDetails;

import java.util.List;

import br.tbm.github.api.app.event.repository.entity.EventCommitsResponse;

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
