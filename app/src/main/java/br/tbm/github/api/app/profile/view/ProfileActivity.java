package br.tbm.github.api.app.profile.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.tbm.github.api.app.profile.view.adapter.RepositoryAdapter;
import br.tbm.github.api.shared.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.app.profile.presenter.ProfilePresenter;
import br.tbm.github.api.app.profile.repository.ProfileRepository;
import br.tbm.github.api.shared.ui.activities.BaseActivity;
import br.tbm.github.api.shared.ui.components.CircleTransform;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.app.profile.repository.entity.RepositoriesResponse;
import br.tbm.github.api.shared.utils.RedirectUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class ProfileActivity extends BaseActivity<RepositoriesResponse> implements
        IProfileView {

    private Profile mProfile;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.activity_profile_imageview)
    ImageView mIvProfile;

    @BindView(R.id.profile_activity_no_repository)
    TextView mTvEmptyDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ButterKnife.bind(this);

        this.mProfile = getIntent().getExtras().getParcelable(Constants.INTENT_PROFILE);

        this.init();

//        initializedSecondThreadIdlingResource();
        ProfilePresenter presenter = new ProfilePresenter(this, new ProfileRepository());
        presenter.searchPublicRepositoriesInServer(mProfile.getLogin());
        presenter.updateScreen(mProfile);
    }

    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
    }

    // ###################
    // CALLBACK DO ADAPTER
    // ###################

    @Override
    public void onClick(RepositoriesResponse response) {
        RedirectUtils.redirectToRepositoryDetailsActivity(this, response.getName(), mProfile.getLogin());
    }

    // ######################
    // CALLBACK DO PRESENTER
    // ######################

    @Override
    public void repositoriesListEmpty() {
        dismissProgressDialog();
        mTvEmptyDescription.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayRepositories(ArrayList<RepositoriesResponse> repositories) {
        dismissProgressDialog();
        mRecyclerView.setAdapter(new RepositoryAdapter(repositories, this));
    }

    @Override
    public void downloadProfileImage(String avatarUrl) {
        Picasso.with(this)
                .load(avatarUrl)
                .fit()
                .error(R.drawable.img_user_not_found)
                .transform(new CircleTransform())
                .into(mIvProfile);
    }

    @Override
    public void updateToolbarTitle(String name) {
        changeToolbarTitle(name);
    }
}