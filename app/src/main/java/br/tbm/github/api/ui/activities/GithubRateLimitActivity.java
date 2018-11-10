package br.tbm.github.api.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import br.tbm.github.api.R;
import br.tbm.github.api.interfaces.GithubRateLimitMVP;
import br.tbm.github.api.presenter.GithubRateLimitPresenter;
import br.tbm.github.api.network.entities.ResourcesResponse;
import br.tbm.github.api.repository.GithubRateLimitRepository;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class GithubRateLimitActivity extends BaseActivity<ResourcesResponse> implements
        GithubRateLimitMVP.View {

    @BindView(R.id.activity_github_core_limit)
    TextView mTvCoreLimit;

    @BindView(R.id.activity_github_core_remaining)
    TextView mTvCoreRemaining;

    @BindView(R.id.activity_github_search_limit)
    TextView mTvSearchLimit;

    @BindView(R.id.activity_github_search_remaining)
    TextView mTvSearchRemaining;

    @BindView(R.id.activity_github_graph_limit)
    TextView mTvGraphLimit;

    @BindView(R.id.activity_github_graph_remaining)
    TextView mTvGraphRemaining;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_rate_limits);

        ButterKnife.bind(this);

        this.init();

        GithubRateLimitPresenter presenter = new GithubRateLimitPresenter(this, new GithubRateLimitRepository());
        presenter.searchRateLimitInServer();
    }

    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(getString(R.string.github_rate_limit_activity_toolbar));
    }

    private void hideFields(TextView tvLimit, TextView tvRemaining) {
        tvLimit.setVisibility(View.INVISIBLE);
        tvRemaining.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setCoreLimit(int message, int limit) {
        mTvCoreLimit.setText(getString(message, limit));
    }

    @Override
    public void setCoreRemaining(int message, int remaining) {
        mTvCoreRemaining.setText(getString(message, remaining));
    }

    @Override
    public void hideCoreFields() {
        this.hideFields(mTvCoreLimit, mTvCoreRemaining);
    }

    @Override
    public void setSearchLimit(int message, int limit) {
        mTvSearchLimit.setText(getString(message, limit));
    }

    @Override
    public void setSearchRemaining(int message, int remaining) {
        mTvSearchRemaining.setText(getString(message, remaining));
    }

    @Override
    public void hideSearchFields() {
        this.hideFields(mTvSearchLimit, mTvSearchRemaining);
    }

    @Override
    public void setGraphLimit(int message, int limit) {
        mTvGraphLimit.setText(getString(message, limit));
    }

    @Override
    public void setGraphRemaining(int message, int remaining) {
        mTvGraphRemaining.setText(getString(message, remaining));
    }

    @Override
    public void hideGraphFields() {
        this.hideFields(mTvGraphLimit, mTvGraphRemaining);
    }

    @Override
    public void hideProgressDialog() {
        dismissProgressDialog();
    }
}
