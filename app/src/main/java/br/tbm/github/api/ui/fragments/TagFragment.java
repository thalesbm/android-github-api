package br.tbm.github.api.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.repository.TagRepository;
import br.tbm.github.api.ui.adapters.BranchesTagsAdapter;
import br.tbm.github.api.interfaces.TagMVP;
import br.tbm.github.api.presenter.TagPresenter;
import br.tbm.github.api.network.entities.BranchesTagsResponse;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class TagFragment extends BaseFragment<BranchesTagsResponse> implements
        TagMVP.View {

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tags, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String repositoryName = getArguments().getString(Constants.INTENT_REPOSITORY);
        String userName = getArguments().getString(Constants.INTENT_USERNAME);

        this.init();

        TagPresenter presenter = new TagPresenter(this, new TagRepository());
        presenter.searchTagsInServer(userName, repositoryName);
    }

    @Override
    protected void init() {
        getAppActivity().changeToolbarTitle(getString(R.string.tags_fragment_title));

        mRecyclerView = getAppActivity().findViewById(R.id.fragment_tags_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = getAppActivity().findViewById(R.id.fragment_tags_textview);
    }

    @Override
    public void listTags(ArrayList<BranchesTagsResponse> tags) {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new BranchesTagsAdapter(tags, false));
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void listTagsEmpty() {
        mTvListEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }
}
