package br.tbm.github.api.app.eventDetails.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import br.tbm.github.api.app.event.repository.entity.EventCommitsResponse;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventDetailsPresenterTest {

    @Mock
    EventDetailsMVP.View mView;

    private EventDetailsPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new EventDetailsPresenter(mView);
    }

    @Test
    public void validateEventsList_EmptyList_Test() {
        mPresenter.validateEventsList(new ArrayList<>());
        verify(mView, atLeastOnce()).listEventsEmpty();
    }

    @Test
    public void validateEventsList_Test() {
        ArrayList<EventCommitsResponse> arrayList = new ArrayList<>();
        arrayList.add(new EventCommitsResponse());
        mPresenter.validateEventsList(arrayList);
        verify(mView, atLeastOnce()).listEvents();
    }
}