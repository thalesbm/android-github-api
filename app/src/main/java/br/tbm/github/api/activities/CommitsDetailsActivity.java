package br.tbm.github.api.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.adapters.CommitDetailsAdapter;
import br.tbm.github.api.components.CircleTransform;
import br.tbm.github.api.entities.CommitsResponse;
import br.tbm.github.api.rest.RestAPI;
import br.tbm.github.api.rest.RestRepository;
import br.tbm.github.api.utils.DateUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitsDetailsActivity extends BaseActivity {

    private String mRepositoryName, mUserName, mSha;

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty, mTvCommitterName, mTvCommitterDate, mTvCommitDescription;
    private ImageView mIvCommitterProfile;

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

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mRecyclerView = findViewById(R.id.activity_commits_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = findViewById(R.id.activity_commits_empty_text_view);
        mTvCommitterName = findViewById(R.id.activity_commit_details_name_textview);
        mTvCommitterDate = findViewById(R.id.activity_commit_created_textview);
        mTvCommitDescription = findViewById(R.id.activity_commit_details_description_textview);

        mIvCommitterProfile = findViewById(R.id.activity_commit_details_imageview);
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
            public void onResponse(@NonNull Call<CommitsResponse> call, @NonNull Response<CommitsResponse> response) {
                dismissProgressDialog();

                if (response.isSuccessful()) {
                    updateScreen(response.body());
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

    /**
     * Metodo para carregar a lista de commits na tela ou exibir a mensagem de lista vazia e
     * carregar o autor do commit
     *
     * @param body CommitsResponse
     */
    private void updateScreen(CommitsResponse body) {
        if (body.getCommitFilesResponse() != null) {

            mTvCommitterName.setText(body.getOwnerResponse().getLogin());
            mTvCommitterDate.setText(DateUtils.formatDate(body.getCommitDetailsResponse().getCommitterResponse().getDate()));
            mTvCommitDescription.setText(body.getCommitDetailsResponse().getMessage());

            // baixa a imagem usando picasso library
            if (!body.getOwnerResponse().getAvatarUrl().equals("")) {
                Picasso.with(this)
                        .load(body.getOwnerResponse().getAvatarUrl())
                        .fit()
                        .error(R.drawable.img_user_not_found)
                        .transform(new CircleTransform())
                        .into(mIvCommitterProfile);
            }

            if (!body.getCommitFilesResponse().isEmpty()) {
                mTvListEmpty.setVisibility(View.GONE);

                mRecyclerView.setAdapter(new CommitDetailsAdapter(body.getCommitFilesResponse()));
                mRecyclerView.setVisibility(View.VISIBLE);
            } else {
                mTvListEmpty.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else {
            displayGenericNetworkIssue(getString(R.string.generic_connection_issue), true);
        }
    }
}
