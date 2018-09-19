package br.tbm.github.api.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.repository.EventRepository;
import br.tbm.github.api.ui.adapters.EventsAdapter;
import br.tbm.github.api.interfaces.EventMVP;
import br.tbm.github.api.presenter.EventPresenter;
import br.tbm.github.api.network.entities.EventPayloadResponse;
import br.tbm.github.api.network.entities.EventsResponse;
import br.tbm.github.api.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class EventFragment extends BaseFragment<EventsResponse> implements
        EventMVP.View {

    private String mRepositoryName, mUserName;

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mRepositoryName = getArguments().getString(Constants.INTENT_REPOSITORY);
        this.mUserName = getArguments().getString(Constants.INTENT_USERNAME);

        this.init();

        EventPresenter presenter = new EventPresenter(this, new EventRepository());
        presenter.searchEventsInServer(mUserName, mRepositoryName);
    }

    @Override
    protected void init() {
        getAppActivity().changeToolbarTitle(getString(R.string.events_fragment_title));

        mRecyclerView = getAppActivity().findViewById(R.id.fragment_events_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = getAppActivity().findViewById(R.id.fragment_events_textview);
    }

    @Override
    public void listEvents(ArrayList<EventsResponse> events) {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new EventsAdapter(events, this));
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void listEventsEmpty() {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    // ###################
    // CALLBACK DO ADAPTER
    // ###################

    @Override
    public void onClick(EventsResponse eventsResponse) {
        super.onClick(eventsResponse);

        EventPayloadResponse eventPayloadResponse = eventsResponse.getEventPayloadResponse();
        eventPayloadResponse.setEventType(eventsResponse.getType());

        RedirectUtils.redirectToEventsDetailsActivity(getAppActivity(), mRepositoryName, mUserName, eventPayloadResponse);
    }
}
