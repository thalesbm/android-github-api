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
import br.tbm.github.api.entities.RepositoriesResponse;
import br.tbm.github.api.interfaces.AdaptersCallbacks;
import br.tbm.github.api.models.Profile;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private AdaptersCallbacks.ProfileAdapterCallback mCallback;
    private List<Profile> mList;
    private Context mContext;

    public ProfileAdapter() {
    }

    public ProfileAdapter(List<Profile> list, AdaptersCallbacks.ProfileAdapterCallback callback) {
        this.mList = list;
        this.mCallback = callback;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvLogin;
        private ImageView mIvProfile;
        private ConstraintLayout mConstraintLayout;

        private ViewHolder(View vi) {
            super(vi);
            mTvName = vi.findViewById(R.id.adapter_name_textview);
            mTvLogin = vi.findViewById(R.id.adapter_login_textview);
            mIvProfile = vi.findViewById(R.id.adapter_profile_imageview);
            mConstraintLayout = vi.findViewById(R.id.adapter_main_layout);
        }
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_profile, parent, false);
        this.mContext = parent.getContext();
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileAdapter.ViewHolder holder, final int position) {
        final Profile profile = mList.get(position);

        holder.mTvName.setText(profile.getName());
        holder.mTvLogin.setText(profile.getLogin());

        holder.mConstraintLayout.setOnLongClickListener((View v) -> {
            mCallback.longClick(position);
            return true;
        });

        Picasso.with(mContext)
                .load(profile.getAvatarUrl())
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