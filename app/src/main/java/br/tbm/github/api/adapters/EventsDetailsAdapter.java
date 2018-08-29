package br.tbm.github.api.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.entities.EventCommitsResponse;

/**
 * Created by thalesbertolini on 27/08/2018
 **/
public class EventsDetailsAdapter extends RecyclerView.Adapter<EventsDetailsAdapter.ViewHolder> {

    private List<EventCommitsResponse> mList;

    public EventsDetailsAdapter() {
    }

    public EventsDetailsAdapter(List<EventCommitsResponse> list) {
        this.mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvDescription;

        private ViewHolder(View vi) {
            super(vi);
            mTvName = vi.findViewById(R.id.adapter_name_textview);
            mTvDescription = vi.findViewById(R.id.adapter_description_textview);
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
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}