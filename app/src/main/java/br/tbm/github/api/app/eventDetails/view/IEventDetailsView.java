package br.tbm.github.api.app.eventDetails.view;

import java.util.List;

import br.tbm.github.api.app.event.repository.entity.EventCommitsResponse;

/**
 * Created by thalesbertolini on 18/09/2018
 **/
public interface IEventDetailsView {
    void listEventsEmpty();
    void listEvents();
}
