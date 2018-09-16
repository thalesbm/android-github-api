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
import br.tbm.github.api.ui.adapters.RepositoryAdapter;
import br.tbm.github.api.ui.components.CircleTransform;
import br.tbm.github.api.interfaces.activities.ProfileMVP;
import br.tbm.github.api.presenter.activities.ProfilePresenter;
import br.tbm.github.api.interfaces.generic.AdaptersCallbacks;
import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import br.tbm.github.api.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class ProfileActivity extends BaseActivity<RepositoriesResponse> implements
        AdaptersCallbacks.DefaultAdapterCallback,
        ProfileMVP.View {

    private ProfilePresenter mController;

    private RecyclerView mRecyclerView;
    private ImageView mIvProfile;
    private TextView mTvEmptyDescription;

    private Profile mProfile;

    private ArrayList<RepositoriesResponse> mBody;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProfile = getIntent().getExtras().getParcelable(Constants.INTENT_PROFILE);

        mController = new ProfilePresenter(this);

        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties();

        this.init();
        this.searchProfileByName();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mIvProfile = findViewById(R.id.activity_profile_imageview);
        mTvEmptyDescription = findViewById(R.id.profile_activity_no_repository);
    }

    /**
     * Metodo responsavel por buscar no servidor a lista de repositorios
     * caso de algum erro durante a chamada o app vai exibir um dialog e voltar para a tela anterior
     * caso seja sucesso o app vai carregar a lista na tela
     */
    private void searchProfileByName() {
        showProgressDialog(getString(R.string.loading));
        initializedSecondThreadIdlingResource();
        mController.search(mProfile.getLogin());
    }

    /**
     * metodo responsavel por receber a lista de repositorios do servidor e carregar na tela
     *
     * @param body lista de repositorios
     */
    private void updateScreen(ArrayList<RepositoriesResponse> body) {
        this.mBody = body;

        if (body.isEmpty()) {
            mTvEmptyDescription.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setAdapter(new RepositoryAdapter(body, this));
        }

        // baixa a imagem usando picasso library
        if (!mProfile.getAvatarUrl().equals("")) {
            Picasso.with(this)
                    .load(mProfile.getAvatarUrl())
                    .fit()
                    .error(R.drawable.img_user_not_found)
                    .transform(new CircleTransform())
                    .into(mIvProfile);
        }

        changeToolbarTitle(mProfile.getName());
    }

    // ###################
    // CALLBACK DO ADAPTER
    // ###################

    @Override
    public void onClick(int position) {
        RedirectUtils.redirectToRepositoryDetailsActivity(this, mBody.get(position).getName(), mProfile.getLogin());
    }

    // ######################
    // CALLBACK DO PRESENTER
    // ######################

    @Override
    public void success(ArrayList<RepositoriesResponse> repositories) {
        super.success(repositories);
        this.updateScreen(repositories);
    }
}