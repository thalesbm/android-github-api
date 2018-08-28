package br.tbm.github.api.fragments;

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
import br.tbm.github.api.adapters.BranchesTagsAdapter;
import br.tbm.github.api.entities.BranchesTagsResponse;
import br.tbm.github.api.rest.RestAPI;
import br.tbm.github.api.rest.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class BranchFragment extends BaseFragment {

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

        getAppActivity().changeToolbarTitle(getString(R.string.branches_fragment_title));

        mRepositoryName = getArguments().getString(Constants.INTENT_REPOSITORY);
        mUserName = getArguments().getString(Constants.INTENT_USERNAME);

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

        RestRepository service = RestAPI.getRetrofitInstance().create(RestRepository.class);
        Call<ArrayList<BranchesTagsResponse>> responseCall = service.listBranches(mUserName, mRepositoryName);
        responseCall.enqueue(new Callback<ArrayList<BranchesTagsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Response<ArrayList<BranchesTagsResponse>> response) {
                dismissProgressDialog();

                if (response.isSuccessful()) {
                    loadList(response.body());
                } else {
                    getAppActivity().analiseRetrofitFailureResponse(response.raw().code(), true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<BranchesTagsResponse>> call, @NonNull Throwable t) {
                getAppActivity().displayGenericNetworkIssue(t.getMessage(), true);
            }
        });
    }

    /**
     * Metodo para carregar a lista de branches na tela ou exibir a mensagem de lista vazia
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
}