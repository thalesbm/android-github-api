package br.tbm.github.api.activities;

import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.adapters.ProfileAdapter;
import br.tbm.github.api.components.CustomActionMode;
import br.tbm.github.api.interfaces.AdaptersCallbacks;
import br.tbm.github.api.interfaces.TasksCallbacks;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.tasks.ListGithubUsersTask;
import br.tbm.github.api.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class MainActivity extends BaseActivity {

    private List<Profile> mProfiles;

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar(findViewById(R.id.toolbar));
        changeToolbarTitle(getString(R.string.main_activity_toolbar));

        this.init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.listProfilesFromDatabase();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {
        mRecyclerView = findViewById(R.id.main_activity_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mTvListEmpty = findViewById(R.id.main_activity_list_description_text_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            RedirectUtils.redirectToSearchByUsernameActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo responsavel por listar todos os perfils ja pesquisados na base de dados
     */
    private void listProfilesFromDatabase() {
        showProgressDialog(getString(R.string.loading));
        new ListGithubUsersTask(new TasksCallbacks.ListGithubUserTaskCallback() {
            @Override
            public void listGithubUserTaskSuccess(List<Profile> profiles) {
                listGithubUserSuccess(profiles);
            }

            @Override
            public void listGithubUserTaskFailure() {
                displayGenericDatabaseIssue();
            }
        }).execute();
    }

    // ####################
    // CALLBACK DAS TASKS
    // ####################

    /**
     * Metodo responsavel por exibir na tela todos os usuarios ja pesquisados e salvos na base de dados
     *
     * @param profiles List<Profile>
     */
    private void listGithubUserSuccess(List<Profile> profiles) {
        this.mProfiles = profiles;
        dismissProgressDialog();
        if (profiles.isEmpty()) {
            mTvListEmpty.setVisibility(View.VISIBLE);
        } else {
            mTvListEmpty.setVisibility(View.GONE);
            mRecyclerView.setAdapter(new ProfileAdapter(profiles, new AdaptersCallbacks.ProfileAdapterCallback() {
                @Override
                public void longClick(int position) {
                    startSupportActionMode(customActionMode);
                }

                @Override
                public void onClick(int position) {
                    RedirectUtils.redirectToProfileActivity(MainActivity.this, mProfiles.get(position), false);
                }
            }));
        }
    }

    /**
     * Metodo responsavel por carregar o menu na toolbar e a acao do onclick button
     */
    private CustomActionMode customActionMode = new CustomActionMode() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // mode.setTitle("Options");
            mode.getMenuInflater().inflate(R.menu.menu_list, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete: {
                    mode.finish();
                    break;
                }
            }
            return true;
        }
    };
}