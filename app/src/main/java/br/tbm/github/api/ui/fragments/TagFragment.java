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
import br.tbm.github.api.repository.fragments.TagRepository;
import br.tbm.github.api.ui.adapters.BranchesTagsAdapter;
import br.tbm.github.api.interfaces.fragments.TagMVP;
import br.tbm.github.api.presenter.fragments.TagPresenter;
import br.tbm.github.api.network.entities.BranchesTagsResponse;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class TagFragment extends BaseFragment<BranchesTagsResponse> implements
        TagMVP.View {

    private TagPresenter mController;

    private String mRepositoryName, mUserName;

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

        mController = new TagPresenter(this, new TagRepository());

        getAppActivity().changeToolbarTitle(getString(R.string.tags_fragment_title));

        mRepositoryName = getArguments().getString(Constants.INTENT_REPOSITORY);
        mUserName = getArguments().getString(Constants.INTENT_USERNAME);

        this.init();
        this.getTagsFromServer();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mRecyclerView = getAppActivity().findViewById(R.id.fragment_tags_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = getAppActivity().findViewById(R.id.fragment_tags_textview);
    }

    /**
     * Metodo responsavel por buscar no servidor a lista de tags
     * caso de algum erro durante a chamada o app vai exibir um dialog e voltar para a tela anterior
     * caso seja sucesso o app vai carregar a lista na tela
     */
    private void getTagsFromServer() {
        showProgressDialog(getString(R.string.loading));
        mController.search(mUserName, mRepositoryName);
    }

    /**
     * Metodo para carregar a lista de tags na tela ou exibir a mensagem de lista vazia
     *
     * @param body ArrayList<BranchesTagsResponse>
     */
    private void loadList(ArrayList<BranchesTagsResponse> body) {
        if (body.isEmpty()) {
            mTvListEmpty.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mTvListEmpty.setVisibility(View.GONE);
            mRecyclerView.setAdapter(new BranchesTagsAdapter(body, false));
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
