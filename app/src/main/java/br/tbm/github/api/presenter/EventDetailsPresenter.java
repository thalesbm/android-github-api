package br.tbm.github.api.presenter;

import java.util.List;

import br.tbm.github.api.interfaces.EventDetailsMVP;
import br.tbm.github.api.network.entities.EventCommitsResponse;

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
