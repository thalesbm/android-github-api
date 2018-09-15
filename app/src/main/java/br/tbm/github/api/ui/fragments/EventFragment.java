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
import java.util.List;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.ui.adapters.EventsAdapter;
import br.tbm.github.api.interfaces.EventMVP;
import br.tbm.github.api.presenter.fragments.EventPresenter;
import br.tbm.github.api.network.entities.EventPayloadResponse;
import br.tbm.github.api.network.entities.EventsResponse;
import br.tbm.github.api.interfaces.generic.AdaptersCallbacks;
import br.tbm.github.api.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class EventFragment extends BaseFragment<EventsResponse> implements
        AdaptersCallbacks.DefaultAdapterCallback,
        EventMVP.View {

    private EventPresenter mController;

    private String mRepositoryName, mUserName;

    private List<EventsResponse> mEventsResponse;

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

        mController = new EventPresenter(this);

        getAppActivity().changeToolbarTitle(getString(R.string.events_fragment_title));

        mRepositoryName = getArguments().getString(Constants.INTENT_REPOSITORY);
        mUserName = getArguments().getString(Constants.INTENT_USERNAME);

        this.init();
        this.getEventsFromServer();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mRecyclerView = getAppActivity().findViewById(R.id.fragment_events_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = getAppActivity().findViewById(R.id.fragment_events_textview);
    }

    /**
     * Metodo responsavel por buscar no servidor a lista de eventos
     * caso de algum erro durante a chamada o app vai exibir um dialog e voltar para a tela anterior
     * caso seja sucesso o app vai carregar a lista na tela
     */
    private void getEventsFromServer() {
        showProgressDialog(getString(R.string.loading));
        mController.search(mUserName, mRepositoryName);
    }

    /**
     * Metodo para carregar a lista de eventos na tela ou exibir a mensagem de lista vazia
     *
     * @param body ArrayList<EventsResponse>
     */
    private void loadList(ArrayList<EventsResponse> body) {
        this.mEventsResponse = body;
        if (body.isEmpty()) {
            mTvListEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mTvListEmpty.setVisibility(View.GONE);

            mRecyclerView.setAdapter(new EventsAdapter(body, this));
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    // ###################
    // CALLBACK DO ADAPTER
    // ###################

    @Override
    public void onClick(int position) {
        EventPayloadResponse selectedEvent = this.mEventsResponse.get(position).getEventPayloadResponse();
        selectedEvent.setEventType(this.mEventsResponse.get(position).getType());

        RedirectUtils.redirectToEventsDetailsActivity(getAppActivity(), mRepositoryName, mUserName, selectedEvent);
    }

    // ######################
    // CALLBACK DO CONTROLLER
    // ######################

    @Override
    public void success(ArrayList<EventsResponse> response) {
        super.success(response);
        this.loadList(response);
    }
}
