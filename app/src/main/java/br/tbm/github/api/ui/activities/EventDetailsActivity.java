package br.tbm.github.api.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.interfaces.EventDetailsMVP;
import br.tbm.github.api.presenter.EventDetailsPresenter;
import br.tbm.github.api.ui.adapters.EventsDetailsAdapter;
import br.tbm.github.api.network.entities.EventPayloadResponse;
import br.tbm.github.api.utils.RedirectUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventDetailsActivity extends BaseActivity implements
        EventDetailsMVP.View {

    private String mRepositoryName, mUserName;
    private EventPayloadResponse mSelectedEvent;

    @BindView(R.id.activity_event_details_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.activity_event_details_empty_list)
    TextView mTvListEmpty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        ButterKnife.bind(this);

        this.mRepositoryName = getIntent().getExtras().getString(Constants.INTENT_REPOSITORY);
        this.mUserName = getIntent().getExtras().getString(Constants.INTENT_USERNAME);
        this.mSelectedEvent = getIntent().getExtras().getParcelable(Constants.INTENT_EVENT);

        this.init();

        EventDetailsMVP.Presenter presenter = new EventDetailsPresenter(this);
        presenter.validateEventsList(mSelectedEvent.getEventCommitsResponse());
    }

    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(mSelectedEvent.getEventType());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
    }

    @Override
    public void listEvents() {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.GONE);

        mRecyclerView.setAdapter(new EventsDetailsAdapter(mSelectedEvent.getEventCommitsResponse(), this));
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
    public void onClick(int position) {
        RedirectUtils.redirectToCommitsDetailsActivity(this, mRepositoryName, mUserName, mSelectedEvent.getEventCommitsResponse().get(position).getSha());
    }
}
