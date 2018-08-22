package br.tbm.github.api.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.entities.RepositoriesResponse;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private List<RepositoriesResponse> mList;

    public RepositoryAdapter() {
    }

    public RepositoryAdapter(List<RepositoriesResponse> list) {
        this.mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvLanguage;

        private ViewHolder(View vi) {
            super(vi);
            mTvName = vi.findViewById(R.id.adapter_name);
            mTvLanguage = vi.findViewById(R.id.adapter_language);
        }
    }

    @NonNull
    @Override
    public RepositoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_repositories, parent, false);
        return new RepositoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RepositoryAdapter.ViewHolder holder, final int position) {
        final RepositoriesResponse git = mList.get(position);

        holder.mTvName.setText(git.getName());
        holder.mTvLanguage.setText(git.getLanguage());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}