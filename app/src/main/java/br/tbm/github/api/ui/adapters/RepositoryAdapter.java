package br.tbm.github.api.ui.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import br.tbm.github.api.interfaces.generic.AdaptersCallbacks;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    private AdaptersCallbacks.DefaultAdapterCallback mCallback;
    private List<RepositoriesResponse> mList;

    public RepositoryAdapter() {
    }

    public RepositoryAdapter(List<RepositoriesResponse> list, AdaptersCallbacks.DefaultAdapterCallback callback) {
        this.mList = list;
        this.mCallback = callback;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvLanguage;
        private ConstraintLayout mConstraintLayout;

        private ViewHolder(View vi) {
            super(vi);
            mTvName = vi.findViewById(R.id.adapter_name_textview);
            mTvLanguage = vi.findViewById(R.id.adapter_language_textview);
            mConstraintLayout = vi.findViewById(R.id.adapter_main_layout);
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

        holder.mConstraintLayout.setOnClickListener((View v) -> {
            mCallback.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}