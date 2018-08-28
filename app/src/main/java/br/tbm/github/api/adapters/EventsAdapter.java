package br.tbm.github.api.adapters;

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
import br.tbm.github.api.components.CircleTransform;
import br.tbm.github.api.entities.EventsResponse;
import br.tbm.github.api.interfaces.AdaptersCallbacks;

import static br.tbm.github.api.utils.DateUtils.formatDate;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private AdaptersCallbacks.DefaultAdapterCallback mCallback;
    private List<EventsResponse> mList;
    private Context mContext;

    public EventsAdapter() {
    }

    public EventsAdapter(List<EventsResponse> list, AdaptersCallbacks.DefaultAdapterCallback callback) {
        this.mList = list;
        this.mCallback = callback;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvType, mTvLogin, mTvCreated;
        private ImageView mIvProfile;
        private ConstraintLayout mConstraintLayout;

        private ViewHolder(View vi) {
            super(vi);
            mTvType = vi.findViewById(R.id.adapter_type_textview);
            mTvLogin = vi.findViewById(R.id.adapter_login_textview);
            mTvCreated = vi.findViewById(R.id.adapter_created_textview);
            mIvProfile = vi.findViewById(R.id.adapter_profile_imageview);
            mConstraintLayout = vi.findViewById(R.id.adapter_main_layout);
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
            mCallback.onClick(position);
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