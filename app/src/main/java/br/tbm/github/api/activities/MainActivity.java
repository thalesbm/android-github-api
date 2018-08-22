package br.tbm.github.api.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.tbm.github.api.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.components.CustomTextWatcher;
import br.tbm.github.api.entities.ProfileResponse;
import br.tbm.github.api.rest.RestAPI;
import br.tbm.github.api.rest.RestUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.tbm.github.api.Constants.HTTP_NOT_FOUND;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class MainActivity extends BaseActivity {

    private TextInputLayout mTvProfile;
    private EditText mEdProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar(findViewById(R.id.toolbar));
        changeToolbarTitle(getString(R.string.main_activity_toolbar));

        this.init();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mTvProfile = findViewById(R.id.main_activity_search_textlayout);
        mEdProfile = findViewById(R.id.main_activity_search_edittext);

        // mEdProfile.setText("thalesbm2");

        mEdProfile.addTextChangedListener(new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                switch (mEdProfile.getId()) {
                    case R.id.main_activity_search_textlayout:
                        validateProfile();
                        break;
                }
            }
        });

        Button btnMovies = findViewById(R.id.main_activity_list_movies_button);
        btnMovies.setOnClickListener((View v) -> {
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
        return validate(getString(R.string.main_activity_profile_validation), mEdProfile, mTvProfile);
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
            showAlertDialog(getString(R.string.no_internet_description), false);
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
        Call<ProfileResponse> responseCall = service.getProfile(profileName);
        responseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileResponse> call, @NonNull Response<ProfileResponse> response) {
                dismissProgressDialog();

                if (response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra(Constants.PROFILE_INTENT, response.body());
                    startActivity(intent);
                } else {
                    if (response.raw().code() == HTTP_NOT_FOUND) {
                        showAlertDialog(getString(R.string.profile_activity_user_not_found), false);
                    } else {
                        showAlertDialog(getString(R.string.profile_activity_generic_issue), false);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileResponse> call, @NonNull Throwable t) {
                dismissProgressDialog();
                showAlertDialog(getString(R.string.profile_activity_generic_issue), false);
            }
        });
    }
}