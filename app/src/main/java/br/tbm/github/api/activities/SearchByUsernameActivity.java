package br.tbm.github.api.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.tbm.github.api.R;
import br.tbm.github.api.components.CustomTextWatcher;
import br.tbm.github.api.controllers.SearchByUsernameController;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class SearchByUsernameActivity extends BaseActivity<Profile> {

    private SearchByUsernameController mController;

    private TextInputLayout mTvProfile;
    private EditText mEdProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_username);

        mController = new SearchByUsernameController(this);

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

        mController.search(profileName);
    }

    // ######################
    // CALLBACK DO CONTROLLER
    // ######################

    @Override
    public void success(Profile profile) {
        super.success(profile);
        RedirectUtils.redirectToProfileActivity(this, profile, true);
    }
}
