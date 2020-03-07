package br.tbm.github.api.app.eventDetails.presenter;

import java.util.List;

import br.tbm.github.api.app.event.repository.entity.EventCommitsResponse;
import br.tbm.github.api.app.eventDetails.view.IEventDetailsView;

/**
 * Created by thalesbertolini on 18/09/2018
 **/
public class EventDetailsPresenter implements IEventDetailsPresenter {

    private IEventDetailsView mView;

    public EventDetailsPresenter(IEventDetailsView view) {
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
