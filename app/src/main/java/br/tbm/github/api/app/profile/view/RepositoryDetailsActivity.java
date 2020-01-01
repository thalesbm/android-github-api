package br.tbm.github.api.app.profile.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import br.tbm.github.api.shared.Constants;
import br.tbm.github.api.R;
import br.tbm.github.api.app.branch.view.BranchFragment;
import br.tbm.github.api.app.event.view.EventFragment;
import br.tbm.github.api.app.tag.view.TagFragment;
import br.tbm.github.api.shared.ui.activities.BaseActivity;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class RepositoryDetailsActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    private String mRepositoryName, mUserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);

        this.mRepositoryName = getIntent().getExtras().getString(Constants.INTENT_REPOSITORY);
        this.mUserName = getIntent().getExtras().getString(Constants.INTENT_USERNAME);

        this.init();

        // exibe o fragment
        this.replaceFragment(new BranchFragment(), true, this.getParameters());
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_events:
                this.replaceFragment(new EventFragment(), false, this.getParameters());
                return true;
            case R.id.navigation_branches:
                this.replaceFragment(new BranchFragment(), false, this.getParameters());
                return true;
            case R.id.navigation_tags:
                this.replaceFragment(new TagFragment(), false, this.getParameters());
                return true;
        }
        return false;
    }

    /**
     * Metodo responsavel por criar o bundle que sera mandado para os fragments
     *
     * @return Bundle
     */
    private Bundle getParameters() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.INTENT_REPOSITORY, mRepositoryName);
        bundle.putString(Constants.INTENT_USERNAME, mUserName);
        return bundle;
    }

    /**
     * Metodo para carregar o fragment na tela
     *
     * @param fragment       Fragment
     * @param addToBackStack Boolean
     */
    private void replaceFragment(Fragment fragment, boolean addToBackStack, Bundle bundle) {

        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_drawer_container, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }

        fragmentTransaction.commit();
    }
}
