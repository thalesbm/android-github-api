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

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class GithubRateLimitActivity extends BaseActivity<ResourcesResponse> implements
        GithubRateLimitMVP.View {

    private TextView mTvCoreLimit, mTvCoreRemaining,
            mTvSearchLimit, mTvSearchRemaining,
            mTvGraphLimit, mTvGraphRemaining;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_rate_limits);

        this.init();

        GithubRateLimitPresenter presenter = new GithubRateLimitPresenter(this, new GithubRateLimitRepository());
        presenter.search();
    }

    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(getString(R.string.github_rate_limit_activity_toolbar));

        this.mTvCoreLimit = findViewById(R.id.activity_github_core_limit);
        this.mTvCoreRemaining = findViewById(R.id.activity_github_core_remaining);
        this.mTvSearchLimit = findViewById(R.id.activity_github_search_limit);
        this.mTvSearchRemaining = findViewById(R.id.activity_github_search_remaining);
        this.mTvGraphLimit = findViewById(R.id.activity_github_graph_limit);
        this.mTvGraphRemaining = findViewById(R.id.activity_github_graph_remaining);
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
}
