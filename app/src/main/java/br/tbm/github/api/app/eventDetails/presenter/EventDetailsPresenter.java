package br.tbm.github.api.app.eventDetails.presenter;

import java.util.List;

import br.tbm.github.api.app.eventDetails.EventDetailsMVP;
import br.tbm.github.api.app.event.repository.entity.EventCommitsResponse;

/**
 * Created by thalesbertolini on 18/09/2018
 **/
public class EventDetailsPresenter implements EventDetailsMVP.Presenter {

    private EventDetailsMVP.View mView;

    public EventDetailsPresenter(EventDetailsMVP.View view) {
        super();
        this.mView = view;
    }

    @Override
    public void validateEventsList(List<EventCommitsResponse> events) {
        if (events != null && !events.isEmpty()) {
            mView.listEvents();
        } else {
            mView.listEventsEmpty();
        }
    }
}
