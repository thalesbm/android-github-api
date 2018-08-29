package br.tbm.github.api.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.entities.CommitsResponse;
import br.tbm.github.api.entities.RepositoriesResponse;
import br.tbm.github.api.rest.RestAPI;
import br.tbm.github.api.rest.RestRepository;
import br.tbm.github.api.rest.RestUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitsDetailsActivity extends BaseActivity {

    private String mRepositoryName, mUserName, mSha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_details);

        this.mRepositoryName = getIntent().getExtras().getString(Constants.INTENT_REPOSITORY);
        this.mUserName = getIntent().getExtras().getString(Constants.INTENT_USERNAME);
        this.mSha = getIntent().getExtras().getString(Constants.INTENT_SHA);

        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(getString(R.string.commit_details_activity_toolbar));

        this.init();
        this.getCommitDetailsFromServer();
    }

    @Override
    protected void init() {

    }

    /**
     * Metodo responsavel por buscar no servidor a lista de commits
     * caso de algum erro durante a chamada o app vai exibir um dialog e voltar para a tela anterior
     * caso seja sucesso o app vai carregar a lista na tela
     */
    private void getCommitDetailsFromServer() {
        showProgressDialog(getString(R.string.loading));

        RestRepository service = RestAPI.getRetrofitInstance().create(RestRepository.class);
        Call<CommitsResponse> responseCall = service.listCommits(mUserName, mRepositoryName, mSha);
        responseCall.enqueue(new Callback<CommitsResponse>() {
            @Override
            public void onResponse(@NonNull Call<CommitsResponse>call, @NonNull Response<CommitsResponse> response) {
                dismissProgressDialog();

                if (response.isSuccessful()) {
                    // updateScreen(response.body());
                } else {
                    analiseRetrofitFailureResponse(response.raw().code(), true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CommitsResponse> call, @NonNull Throwable t) {
                displayGenericNetworkIssue(t.getMessage(), true);
            }
        });
    }
}
