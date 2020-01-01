package br.tbm.github.api.app.event.view;

import java.util.ArrayList;

import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IEventView extends BaseViewCallbacks<EventsResponse> {
    void listEventsEmpty();
    void listEvents(ArrayList<EventsResponse> events);
}
