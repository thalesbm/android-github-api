package br.tbm.github.api.app.event.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.app.event.repository.entity.EventsResponse;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EventPresenterTest {

    @Mock
    EventMVP.View mView;

    @Mock
    EventMVP.Model mModel;

    private EventPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new EventPresenter(mView, mModel);
    }

    @Test
    public void searchEventsInServer_Test() {
        mPresenter.searchEventsInServer("thalesbm92", "android-github-api");
        verify(mView, atLeastOnce()).updateProgressDialog(R.string.loading);
        verify(mModel, atLeastOnce()).searchEventsInServer("thalesbm92", "android-github-api");
    }

    @Test
    public void displayAlertDialog_Test() {
        mPresenter.displayAlertDialog(500);
        verify(mView, atLeastOnce()).displayAlertDialog(500, true);
    }

    @Test
    public void success_EmptyList_Test() {
        mPresenter.success(new ArrayList<>());
        verify(mView, atLeastOnce()).listEventsEmpty();
    }

    @Test
    public void success_Test() {
        ArrayList<EventsResponse> arrayList = new ArrayList<>();
        arrayList.add(new EventsResponse());
        mPresenter.success(arrayList);
        verify(mView, atLeastOnce()).listEvents(arrayList);
    }

    @Test
    public void networkIssue_Test() {
        mPresenter.networkIssue(500);
        verify(mView, atLeastOnce()).networkIssue(500, true);
    }
}