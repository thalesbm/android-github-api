package br.tbm.github.api.app.branch.ui;

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

import br.tbm.github.api.shared.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.app.branch.BranchMVP;
import br.tbm.github.api.app.branch.presenter.BranchPresenter;
import br.tbm.github.api.app.branch.repository.BranchRepository;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.shared.GithubApplication;
import br.tbm.github.api.shared.ui.fragments.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class BranchFragment extends BaseFragment<BranchesTagsResponse> implements
        BranchMVP.View {

    @BindView(R.id.fragment_branches_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.fragment_branches_textview)
    TextView mTvListEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_branches, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String repositoryName = getArguments().getString(Constants.INTENT_REPOSITORY);
        String userName = getArguments().getString(Constants.INTENT_USERNAME);

        this.init();

        BranchPresenter presenter = new BranchPresenter(this, new BranchRepository(GithubApplication.getRetrofitInstance()));
        presenter.searchBranchesInServer(userName, repositoryName);
    }

    @Override
    protected void init() {
        getAppActivity().changeToolbarTitle(getString(R.string.branches_fragment_title));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
    }

    @Override
    public void branchesListEmpty() {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void branchesList(ArrayList<BranchesTagsResponse> branches) {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new BranchesTagsAdapter(branches, true));
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}