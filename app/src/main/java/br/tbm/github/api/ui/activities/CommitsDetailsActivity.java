package br.tbm.github.api.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.repository.activities.CommitDetailsRepository;
import br.tbm.github.api.ui.adapters.CommitDetailsAdapter;
import br.tbm.github.api.ui.components.CircleTransform;
import br.tbm.github.api.presenter.activities.CommitsDetailsPresenter;
import br.tbm.github.api.network.entities.CommitsResponse;
import br.tbm.github.api.interfaces.activities.CommitDetailsMVP;
import br.tbm.github.api.utils.DateUtils;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitsDetailsActivity extends BaseActivity<CommitsResponse> implements
        CommitDetailsMVP.View {

    private CommitsDetailsPresenter mController;

    private String mRepositoryName, mUserName, mSha;

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty, mTvCommitterName, mTvCommitterDate, mTvCommitDescription;
    private ImageView mIvCommitterProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_details);

        this.mController = new CommitsDetailsPresenter(this, new CommitDetailsRepository());

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
        mController.search(mUserName, mRepositoryName, mSha);
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

    // ######################
    // CALLBACK DO CONTROLLER
    // ######################

    @Override
    public void success(CommitsResponse response) {
        super.success(response);
        this.updateScreen(response);
    }
}
