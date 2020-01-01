package br.tbm.github.api.app.searchByUsername.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.tbm.github.api.R;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.app.searchByUsername.presenter.SearchByUsernamePresenter;
import br.tbm.github.api.app.searchByUsername.repository.SearchByUsernameRepository;
import br.tbm.github.api.shared.ui.activities.BaseActivity;
import br.tbm.github.api.shared.utils.RedirectUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class SearchByUsernameActivity extends BaseActivity<Profile> implements
        ISearchByUsernameView {

    private SearchByUsernamePresenter mPresenter;

    @BindView(R.id.search_activity_search_textlayout)
    TextInputLayout mTvProfile;

    @BindView(R.id.search_activity_search_edittext)
    EditText mEdProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_username);

        ButterKnife.bind(this);

        this.mPresenter = new SearchByUsernamePresenter(this, new SearchByUsernameRepository(this));

        this.init();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties(getString(R.string.search_activity_toolbar));

        Button btnSearch = findViewById(R.id.search_activity_button);
        btnSearch.setOnClickListener((View v) -> {
            mPresenter.validateFields(mEdProfile.getText().toString());
        });
    }

    @Override
    public void validateNotPassed(int message) {
        mTvProfile.setError(getString(message));
    }

    @Override
    public void validatePassed() {
        mTvProfile.setErrorEnabled(false);
    }

    // ######################
    // CALLBACK DO PRESENTER
    // ######################

    @Override
    public void success(Profile profile) {
        super.success(profile);
        RedirectUtils.redirectToProfileActivity(this, profile, true);
    }
}
