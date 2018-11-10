package br.tbm.github.api.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.network.entities.CommitFilesResponse;
import br.tbm.github.api.repository.CommitDetailsRepository;
import br.tbm.github.api.ui.adapters.CommitDetailsAdapter;
import br.tbm.github.api.ui.components.CircleTransform;
import br.tbm.github.api.presenter.CommitsDetailsPresenter;
import br.tbm.github.api.network.entities.CommitsResponse;
import br.tbm.github.api.interfaces.CommitDetailsMVP;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitsDetailsActivity extends BaseActivity<CommitsResponse> implements
        CommitDetailsMVP.View {

    @BindView(R.id.activity_commits_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.activity_commits_empty_text_view)
    TextView mTvListEmpty;

    @BindView(R.id.activity_commit_details_name_textview)
    TextView mTvCommitterName;

    @BindView(R.id.activity_commit_created_textview)
    TextView mTvCommitterDate;

    @BindView(R.id.activity_commit_details_description_textview)
    TextView mTvCommitDescription;

    @BindView(R.id.activity_commit_details_imageview)
    ImageView mIvCommitterProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_details);

        ButterKnife.bind(this);

        CommitsDetailsPresenter presenter = new CommitsDetailsPresenter(this, new CommitDetailsRepository());

        String repositoryName = getIntent().getExtras().getString(Constants.INTENT_REPOSITORY);
        String userName = getIntent().getExtras().getString(Constants.INTENT_USERNAME);
        String sha = getIntent().getExtras().getString(Constants.INTENT_SHA);

        this.init();

        presenter.searchCommitDetailsInServer(userName, repositoryName, sha);
    }

    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(getString(R.string.commit_details_activity_toolbar));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
    }

    @Override
    public void setCommitterName(String login) {
        mTvCommitterName.setText(login);
    }

    @Override
    public void setCommitDescription(String message) {
        mTvCommitDescription.setText(message);
    }

    @Override
    public void setCommitterDate(String date) {
        mTvCommitterDate.setText(date);
    }

    @Override
    public void listCommitsEmpty() {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void downloadProfileImage(String avatarUrl) {
        Picasso.with(this)
                .load(avatarUrl)
                .fit()
                .error(R.drawable.img_user_not_found)
                .transform(new CircleTransform())
                .into(mIvCommitterProfile);
    }

    @Override
    public void listCommits(List<CommitFilesResponse> commits) {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.GONE);

        mRecyclerView.setAdapter(new CommitDetailsAdapter(commits));
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
