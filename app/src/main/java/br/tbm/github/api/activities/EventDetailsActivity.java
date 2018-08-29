package br.tbm.github.api.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.adapters.EventsAdapter;
import br.tbm.github.api.adapters.EventsDetailsAdapter;
import br.tbm.github.api.entities.EventPayloadResponse;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventDetailsActivity extends BaseActivity {

    private String mRepositoryName, mUserName;
    private EventPayloadResponse mSelectedEvent;

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        this.mRepositoryName = getIntent().getExtras().getString(Constants.INTENT_REPOSITORY);
        this.mUserName = getIntent().getExtras().getString(Constants.INTENT_USERNAME);
        this.mSelectedEvent = getIntent().getExtras().getParcelable(Constants.INTENT_EVENT);

        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(mSelectedEvent.getEventType());

        this.init();
        this.listEvents();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mRecyclerView = findViewById(R.id.activity_event_details_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = findViewById(R.id.activity_event_details_empty_list);
    }

    /**
     * Metodo para carregar a lista de eventos na tela ou exibir a mensagem de lista vazia
     */
    private void listEvents() {
        if (mSelectedEvent.getEventCommitsResponse() != null && !mSelectedEvent.getEventCommitsResponse().isEmpty()) {
            mTvListEmpty.setVisibility(View.GONE);

            mRecyclerView.setAdapter(new EventsDetailsAdapter(mSelectedEvent.getEventCommitsResponse()));
            mRecyclerView.setVisibility(View.VISIBLE);

        } else {
            mTvListEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }
}
