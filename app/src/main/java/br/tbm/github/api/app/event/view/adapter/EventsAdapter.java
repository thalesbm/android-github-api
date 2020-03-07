package br.tbm.github.api.app.event.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import br.tbm.github.api.commons.view.adapters.AdaptersCallbacks;
import br.tbm.github.api.commons.view.components.CircleTransform;
import butterknife.BindView;
import butterknife.ButterKnife;

import static br.tbm.github.api.commons.utils.DateUtils.formatDate;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private AdaptersCallbacks.DefaultAdapterCallback<EventsResponse> mCallback;
    private List<EventsResponse> mList;
    private Context mContext;

    public EventsAdapter() {
    }

    public EventsAdapter(List<EventsResponse> list, AdaptersCallbacks.DefaultAdapterCallback<EventsResponse> callback) {
        this.mList = list;
        this.mCallback = callback;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_type_textview)
        TextView mTvType;

        @BindView(R.id.adapter_login_textview)
        TextView mTvLogin;

        @BindView(R.id.adapter_created_textview)
        TextView mTvCreated;

        @BindView(R.id.adapter_profile_imageview)
        ImageView mIvProfile;

        @BindView(R.id.adapter_main_layout)
        ConstraintLayout mConstraintLayout;

        private ViewHolder(View vi) {
            super(vi);
            ButterKnife.bind(this, vi);
        }
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_events, parent, false);
        this.mContext = parent.getContext();
        return new EventsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsAdapter.ViewHolder holder, final int position) {
        final EventsResponse git = mList.get(position);

        holder.mTvType.setText(git.getType());
        holder.mTvLogin.setText(git.getEventActorResponse().getLogin());
        holder.mTvCreated.setText(formatDate(git.getCreated()));

        holder.mConstraintLayout.setOnClickListener((View v) -> {
            mCallback.onClick(mList.get(position));
        });

        Picasso.with(mContext)
                .load(git.getEventActorResponse().getAvatarUrl())
                .fit()
                .error(R.drawable.img_user_not_found)
                .transform(new CircleTransform())
                .into(holder.mIvProfile);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}