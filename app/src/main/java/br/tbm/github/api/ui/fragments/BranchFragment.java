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
import br.tbm.github.api.repository.BranchRepository;
import br.tbm.github.api.ui.adapters.BranchesTagsAdapter;
import br.tbm.github.api.interfaces.BranchMVP;
import br.tbm.github.api.presenter.BranchPresenter;
import br.tbm.github.api.network.entities.BranchesTagsResponse;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class BranchFragment extends BaseFragment<BranchesTagsResponse> implements
        BranchMVP.View {

    private BranchPresenter mPresenter;

    private String mRepositoryName, mUserName;

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_branches, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mPresenter = new BranchPresenter(this, new BranchRepository());

        getAppActivity().changeToolbarTitle(getString(R.string.branches_fragment_title));

        this.mRepositoryName = getArguments().getString(Constants.INTENT_REPOSITORY);
        this.mUserName = getArguments().getString(Constants.INTENT_USERNAME);

        this.init();
        this.getBranchesFromServer();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mRecyclerView = getAppActivity().findViewById(R.id.fragment_branches_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = getAppActivity().findViewById(R.id.fragment_branches_textview);
    }

    /**
     * Metodo responsavel por buscar no servidor a lista de branches
     * caso de algum erro durante a chamada o app vai exibir um dialog e voltar para a tela anterior
     * caso seja sucesso o app vai carregar a lista na tela
     */
    private void getBranchesFromServer() {
        showProgressDialog(getString(R.string.loading));
        mPresenter.search(mUserName, mRepositoryName);
    }

    /**
     * Metodo para carregar a lista de branches na tela ou exibir a mensagem de lista vazia
     *
     * @param body ArrayList<BranchesTagsResponse>
     */
    private void loadList(ArrayList<BranchesTagsResponse> body) {
        if (body.isEmpty()) {
            mTvListEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mTvListEmpty.setVisibility(View.GONE);
            mRecyclerView.setAdapter(new BranchesTagsAdapter(body, true));
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    // ######################
    // CALLBACK DO CONTROLLER
    // ######################

    @Override
    public void success(ArrayList<BranchesTagsResponse> response) {
        super.success(response);
        this.loadList(response);
    }
}