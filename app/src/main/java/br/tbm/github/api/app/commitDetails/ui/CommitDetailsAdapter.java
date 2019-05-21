package br.tbm.github.api.app.commitDetails.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitFilesResponse;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 30/08/2018
 **/
public class CommitDetailsAdapter extends RecyclerView.Adapter<CommitDetailsAdapter.ViewHolder> {

    private List<CommitFilesResponse> mList;

    public CommitDetailsAdapter() {
    }

    public CommitDetailsAdapter(List<CommitFilesResponse> list) {
        this.mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_name_textview)
        TextView mTvName;

        @BindView(R.id.adapter_description_textview)
        TextView mTvDescription;

        private ViewHolder(View vi) {
            super(vi);
            ButterKnife.bind(this, vi);
        }
    }

    @NonNull
    @Override
    public CommitDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_commit_details, parent, false);
        return new CommitDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CommitDetailsAdapter.ViewHolder holder, final int position) {
        final CommitFilesResponse git = mList.get(position);

        holder.mTvName.setText(git.getFileName());
        holder.mTvDescription.setText(git.getStatus());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}