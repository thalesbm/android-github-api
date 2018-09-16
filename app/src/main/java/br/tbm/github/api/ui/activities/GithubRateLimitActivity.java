package br.tbm.github.api.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import br.tbm.github.api.R;
import br.tbm.github.api.interfaces.activities.GithubRateLimitMVP;
import br.tbm.github.api.presenter.activities.GithubRateLimitPresenter;
import br.tbm.github.api.network.entities.ResourcesResponse;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class GithubRateLimitActivity extends BaseActivity<ResourcesResponse> implements
        GithubRateLimitMVP.View {

    private GithubRateLimitPresenter mController;

    private TextView mTvCoreLimit, mTvCoreRemaining,
            mTvSearchLimit, mTvSearchRemaining,
            mTvGraphLimit, mTvGraphRemaining;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_rate_limits);

        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(getString(R.string.github_rate_limit_activity_toolbar));

        this.mController = new GithubRateLimitPresenter(this);

        this.init();
        this.searchEvents();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        this.mTvCoreLimit = findViewById(R.id.activity_github_core_limit);
        this.mTvCoreRemaining = findViewById(R.id.activity_github_core_remaining);
        this.mTvSearchLimit = findViewById(R.id.activity_github_search_limit);
        this.mTvSearchRemaining = findViewById(R.id.activity_github_search_remaining);
        this.mTvGraphLimit = findViewById(R.id.activity_github_graph_limit);
        this.mTvGraphRemaining = findViewById(R.id.activity_github_graph_remaining);
    }

    /**
     * Metodo responsavel por buscar no servidor a quantidade de requisicoes que ja foram feitas
     * e quantas faltam para atingir o limite
     * caso de algum erro durante a chamada o app vai exibir um dialog e voltar para a tela anterior
     * caso seja sucesso o app vai carregar a informacao na tela
     */
    private void searchEvents() {
        showProgressDialog(getString(R.string.loading));
        mController.search();
    }

    /**
     * Metodo para atualizar a tela com o resultado da pesquisa no servidor
     *
     * @param events ResourcesResponse
     */
    private void updateScreen(ResourcesResponse events) {
        if (events.getRateLimitResponse() != null) {

            // verifica se o objeto core é null, caso nao seja preencha com as informacoes do servidor
            if (events.getRateLimitResponse().getCore() != null) {
                mTvCoreLimit.setText(getString(R.string.github_rate_limit_activity_limit, events.getRateLimitResponse().getCore().getLimit()));
                mTvCoreRemaining.setText(getString(R.string.github_rate_limit_activity_remaining, events.getRateLimitResponse().getCore().getRemaining()));
            } else {
                this.hideFields(mTvCoreLimit, mTvCoreRemaining);
            }

            // verifica se o objeto search é null, caso nao seja preencha com as informacoes do servidor
            if (events.getRateLimitResponse().getSearch() != null) {
                mTvSearchLimit.setText(getString(R.string.github_rate_limit_activity_limit, events.getRateLimitResponse().getSearch().getLimit()));
                mTvSearchRemaining.setText(getString(R.string.github_rate_limit_activity_remaining, events.getRateLimitResponse().getSearch().getRemaining()));
            } else {
                this.hideFields(mTvSearchLimit, mTvSearchRemaining);
            }

            // verifica se o objeto graph é null, caso nao seja preencha com as informacoes do servidor
            if (events.getRateLimitResponse().getGraphql() != null) {
                mTvGraphLimit.setText(getString(R.string.github_rate_limit_activity_limit, events.getRateLimitResponse().getGraphql().getLimit()));
                mTvGraphRemaining.setText(getString(R.string.github_rate_limit_activity_remaining, events.getRateLimitResponse().getGraphql().getRemaining()));
            } else {
                this.hideFields(mTvGraphLimit, mTvGraphRemaining);
            }
        }
    }

    /**
     * Metodo para esconder os campos caso objeto venha null
     *
     * @param tvLimit     TextView
     * @param tvRemaining TextView
     */
    private void hideFields(TextView tvLimit, TextView tvRemaining) {
        tvLimit.setVisibility(View.INVISIBLE);
        tvRemaining.setVisibility(View.INVISIBLE);
    }

    // ######################
    // CALLBACK DO CONTROLLER
    // ######################

    @Override
    public void success(ResourcesResponse events) {
        super.success(events);
        this.updateScreen(events);
    }
}
