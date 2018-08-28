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

import java.util.ArrayList;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.adapters.RepositoryAdapter;
import br.tbm.github.api.components.CircleTransform;
import br.tbm.github.api.interfaces.AdaptersCallbacks;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.entities.RepositoriesResponse;
import br.tbm.github.api.rest.RestAPI;
import br.tbm.github.api.rest.RestUser;
import br.tbm.github.api.utils.RedirectUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class ProfileActivity extends BaseActivity implements
        AdaptersCallbacks.DefaultAdapterCallback {

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

        RestUser service = RestAPI.getRetrofitInstance().create(RestUser.class);
        Call<ArrayList<RepositoriesResponse>> responseCall = service.listRepositories(mProfile.getLogin());
        responseCall.enqueue(new Callback<ArrayList<RepositoriesResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<RepositoriesResponse>> call, @NonNull Response<ArrayList<RepositoriesResponse>> response) {
                dismissProgressDialog();

                if (response.isSuccessful()) {
                    updateScreen(response.body());
                } else {
                    analiseRetrofitFailureResponse(response.raw().code(), true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<RepositoriesResponse>> call, @NonNull Throwable t) {
                dismissProgressDialog();
                showAlertDialog(t.getMessage(), true);
            }
        });
    }

    /**
     * Metodo responsavel por retornar a lista de repositorios do server para testes
     *
     * @return ArrayList<RepositoriesResponse>
     */
    public ArrayList<RepositoriesResponse> getBody() {
        return mBody;
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
}