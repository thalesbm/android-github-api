package br.tbm.github.api.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.tbm.github.api.R;
import br.tbm.github.api.components.CustomTextWatcher;
import br.tbm.github.api.interfaces.TasksCallbacks;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.rest.RestAPI;
import br.tbm.github.api.rest.RestUser;
import br.tbm.github.api.tasks.SaveGithubUserTask;
import br.tbm.github.api.utils.RedirectUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.tbm.github.api.Constants.HTTP_NOT_FOUND;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class SearchByUsernameActivity extends BaseActivity implements
        TasksCallbacks.SaveGithubUserTaskCallback {

    private TextInputLayout mTvProfile;
    private EditText mEdProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_username);

        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(getString(R.string.search_activity_toolbar));

        this.init();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mTvProfile = findViewById(R.id.search_activity_search_textlayout);
        mEdProfile = findViewById(R.id.search_activity_search_edittext);

        // mEdProfile.setText("thalesbm2");

        mEdProfile.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                switch (mEdProfile.getId()) {
                    case R.id.search_activity_search_textlayout:
                        validateProfile();
                        break;
                }
            }
        });

        Button btnSearch = findViewById(R.id.search_activity_button);
        btnSearch.setOnClickListener((View v) -> {
            this.redirectToSearchProfile();
        });
    }

    /**
     * valida se o perfil foi preenchido ou nao
     * caso SIM, retorna true
     * caso NAO, retorna false
     *
     * @return boolean
     */
    private boolean validateProfile() {
        return validate(getString(R.string.search_activity_profile_validation), mEdProfile, mTvProfile);
    }

    /**
     * verifica se o dispositivo esta conectado a internet,
     * caso SIM redireciona para a tela de pesquisar perfil, verifica se o perfil foi preenchido
     * caso NAO, chama o metodo para exibir o dialog
     */
    private void redirectToSearchProfile() {
        if (isOnline()) {
            if (validateProfile()) {
                hideKeyboard();
                this.searchProfileByName(mEdProfile.getText().toString());
            }

        } else {
            showAlertDialog(getString(R.string.generic_internet_issue), false);
        }
    }

    /**
     * Metodo responsavel por buscar no servidor a lista de repositorios
     * caso de algum erro durante a chamada o app vai exibir um dialog e voltar para a tela anterior
     * caso seja sucesso o app vai carregar a lista na tela
     */
    private void searchProfileByName(String profileName) {
        showProgressDialog(getString(R.string.loading));

        RestUser service = RestAPI.getRetrofitInstance().create(RestUser.class);
        Call<Profile> responseCall = service.getProfile(profileName);
        responseCall.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                searchProfileResponseSuccess(response);
            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                displayGenericNetworkIssue();
            }
        });
    }

    // ####################
    // CALLBACK DO SERVIDOR
    // ####################

    /**
     * Metodo responsavel pela logica de sucesso da chamada de pesquisar pelo username
     *
     * @param response Response<Profile>
     */
    private void searchProfileResponseSuccess(final Response<Profile> response) {
        if (response.isSuccessful()) {

            if (response.body() != null && response.body().getName() != null) {
                new SaveGithubUserTask(this).execute(response.body());
            } else {
                showAlertDialog(getString(R.string.search_activity_user_not_found), false);
            }
        } else {
            analiseRetrofitFailureResponse(response.raw().code(), false);
        }
    }

    // ################
    // CALLBACK DA TASK
    // ################

    /**
     * Metodo responsavel por fechar o progress dialog e redirecionar para a tela de perfil
     *
     * @param profile Profile
     */
    @Override
    public void saveGithubUserTaskSuccess(Profile profile) {
        dismissProgressDialog();
        RedirectUtils.redirectToProfileActivity(this, profile, true);
    }

    @Override
    public void saveGithubUserTaskFailure() {
        displayGenericDatabaseIssue();
    }
}
