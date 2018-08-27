package br.tbm.github.api.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import br.tbm.github.api.R;
import br.tbm.github.api.fragments.BranchFragment;
import br.tbm.github.api.fragments.EventFragment;
import br.tbm.github.api.fragments.TagFragment;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class RepositoryDetailsActivity extends BaseActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_details);

        setupToolbar(findViewById(R.id.toolbar));
        setToolbarProperties("Teste");

        this.init();

        // exibe o fragment
        this.replaceFragment(new BranchFragment(), true);
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_events:
                this.replaceFragment(new EventFragment(), false);
                return true;
            case R.id.navigation_branches:
                this.replaceFragment(new BranchFragment(), false);
                return true;
            case R.id.navigation_tags:
                this.replaceFragment(new TagFragment(), false);
                return true;
        }
        return false;
    }

    /**
     * Metodo para carregar o fragment na tela
     *
     * @param fragment Fragment
     * @param addToBackStack Boolean
     */
    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_drawer_container, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }

        fragmentTransaction.commit();
    }
}
