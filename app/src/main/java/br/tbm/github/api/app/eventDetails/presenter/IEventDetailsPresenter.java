package br.tbm.github.api.app.eventDetails.presenter;

import java.util.List;

import br.tbm.github.api.app.event.repository.entity.EventCommitsResponse;

/**
 * Created by thalesbertolini on 18/09/2018
 **/
public interface IEventDetailsPresenter {
    void validateEventsList(List<EventCommitsResponse> events);
}
