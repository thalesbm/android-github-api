package br.tbm.github.api.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.network.entities.BranchesTagsResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 27/08/2018
 **/
public class BranchesTagsAdapter extends RecyclerView.Adapter<BranchesTagsAdapter.ViewHolder> {

    private List<BranchesTagsResponse> mList;
    private boolean mIsBranches;

    public BranchesTagsAdapter() {
    }

    public BranchesTagsAdapter(List<BranchesTagsResponse> list, boolean isBranches) {
        this.mList = list;
        this.mIsBranches = isBranches;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_name_textview)
        TextView mTvName;

        @BindView(R.id.adapter_imageview)
        ImageView mIvIcon;

        private ViewHolder(View vi) {
            super(vi);
            ButterKnife.bind(this, vi);
        }
    }

    @NonNull
    @Override
    public BranchesTagsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_branches_tags, parent, false);
        return new BranchesTagsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BranchesTagsAdapter.ViewHolder holder, final int position) {
        final BranchesTagsResponse git = mList.get(position);

        holder.mTvName.setText(git.getName());

        if (mIsBranches) {
            holder.mIvIcon.setBackgroundResource(R.drawable.ic_branch_list_grey_48dp);
        } else {
            holder.mIvIcon.setBackgroundResource(R.drawable.ic_tags_list_grey_48dp);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}