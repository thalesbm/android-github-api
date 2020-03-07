package br.tbm.github.api.app.eventDetails.view.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.event.repository.entity.EventCommitsResponse;
import br.tbm.github.api.commons.view.adapters.AdaptersCallbacks;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 27/08/2018
 **/
public class EventsDetailsAdapter extends RecyclerView.Adapter<EventsDetailsAdapter.ViewHolder> {

    private List<EventCommitsResponse> mList;
    private AdaptersCallbacks.DefaultAdapterCallback mCallback;

    public EventsDetailsAdapter() {
    }

    public EventsDetailsAdapter(List<EventCommitsResponse> list, AdaptersCallbacks.DefaultAdapterCallback callback) {
        this.mCallback = callback;
        this.mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_name_textview)
        TextView mTvName;

        @BindView(R.id.adapter_description_textview)
        TextView mTvDescription;

        @BindView(R.id.adapter_main_layout)
        ConstraintLayout mConstraintLayout;

        private ViewHolder(View vi) {
            super(vi);
            ButterKnife.bind(this, vi);
        }
    }

    @NonNull
    @Override
    public EventsDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_events_details, parent, false);
        return new EventsDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsDetailsAdapter.ViewHolder holder, final int position) {
        final EventCommitsResponse git = mList.get(position);

        holder.mTvName.setText(git.getEventAuthorResponse().getName());
        holder.mTvDescription.setText(git.getMessage());

        holder.mConstraintLayout.setOnClickListener((View v) -> {
            mCallback.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}