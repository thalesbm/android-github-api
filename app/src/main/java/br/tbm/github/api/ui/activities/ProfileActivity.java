package br.tbm.github.api.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.repository.ProfileRepository;
import br.tbm.github.api.ui.adapters.RepositoryAdapter;
import br.tbm.github.api.ui.components.CircleTransform;
import br.tbm.github.api.interfaces.ProfileMVP;
import br.tbm.github.api.presenter.ProfilePresenter;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import br.tbm.github.api.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class ProfileActivity extends BaseActivity<RepositoriesResponse> implements
        ProfileMVP.View {

    private RecyclerView mRecyclerView;
    private ImageView mIvProfile;
    private TextView mTvEmptyDescription;

    private Profile mProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.mProfile = getIntent().getExtras().getParcelable(Constants.INTENT_PROFILE);

        this.init();

        initializedSecondThreadIdlingResource();
        ProfilePresenter presenter = new ProfilePresenter(this, new ProfileRepository());
        presenter.searchPublicRepositoriesInServer(mProfile.getLogin());
        presenter.updateScreen(mProfile);
    }

    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties();

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mIvProfile = findViewById(R.id.activity_profile_imageview);
        mTvEmptyDescription = findViewById(R.id.profile_activity_no_repository);
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