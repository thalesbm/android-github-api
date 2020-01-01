package br.tbm.github.api.app.listProfiles.view;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.listProfiles.presenter.ListProfilesPresenter;
import br.tbm.github.api.app.listProfiles.repository.ListProfilesRepository;
import br.tbm.github.api.app.listProfiles.view.adapter.ProfileAdapter;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.ui.activities.BaseActivity;
import br.tbm.github.api.shared.ui.adapters.AdaptersCallbacks;
import br.tbm.github.api.shared.ui.components.CustomActionMode;
import br.tbm.github.api.shared.utils.RedirectUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class ListProfilesActivity extends BaseActivity<List<Profile>> implements
        AdaptersCallbacks.ProfileAdapterCallback,
        IListProfilesView {

    private final String TAG = ListProfilesActivity.class.getSimpleName();

    private ListProfilesPresenter mPresenter;

    private List<Profile> mProfiles;

    private ActionMode mCurrentActionMode;

    @BindView(R.id.main_activity_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.main_activity_list_description_text_view)
    TextView mTvListEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_profiles);

        ButterKnife.bind(this);

        mPresenter = new ListProfilesPresenter(this, new ListProfilesRepository());

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
        setupToolbar(findViewById(R.id.toolbar));
        changeToolbarTitle(getString(R.string.main_activity_toolbar));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            RedirectUtils.redirectToSearchByUsernameActivity(this);
            return true;
        }

        if (id == R.id.action_rate_info) {
            RedirectUtils.redirectToGithubRateLimitActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo responsavel por listar todos os perfils ja pesquisados na base de dados
     */
    private void listProfilesFromDatabase() {
//        initializedSecondThreadIdlingResource();
        mPresenter.getProfilesInDatabase();
    }

    /**
     * Metodo responsavel por carregar o menu na toolbar e a acao do onclick button
     */
    CustomActionMode customActionMode = new CustomActionMode() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mCurrentActionMode = mode;
            mode.setTitle("1");
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            menu.findItem(R.id.action_delete).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete: {
//                    initializedSecondThreadIdlingResource();
                    mPresenter.removeItems(mProfiles);
                    mode.finish();
                    break;
                }
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mode.finish();
            listProfilesFromDatabase();
        }
    };

    /**
     * Retorna a quantidade de items na lista
     *
     * @return int
     */
    @VisibleForTesting
    public int getListSize() {
        return mProfiles.size();
    }

    // ###################
    // CALLBACK DO ADAPTER
    // ###################

    @Override
    public void longClick(int position) {
        Log.d(TAG, "longClick(): " + position);
        mProfiles.get(position).setHasSelected(true);
        startSupportActionMode(customActionMode);
    }

    @Override
    public void onClick(int position) {
        RedirectUtils.redirectToProfileActivity(ListProfilesActivity.this, mProfiles.get(position), false);
    }

    @Override
    public void removeSelection(int position, boolean resetActionMode) {
        Log.d(TAG, "removeSelection(): " + position);
        mProfiles.get(position).setHasSelected(false);

        if (resetActionMode) {
            mCurrentActionMode.finish();
        } else {
            mCurrentActionMode.setTitle(mPresenter.checkNumberOfItemsHasBeenChecked(mProfiles));
        }
    }

    @Override
    public void addSelection(int position) {
        Log.d(TAG, "addSelection(): " + position);
        mProfiles.get(position).setHasSelected(true);
        mCurrentActionMode.setTitle(mPresenter.checkNumberOfItemsHasBeenChecked(mProfiles));
    }

    // ######################
    // CALLBACK DO PRESENTER
    // ######################

    @Override
    public void listProfilesEmpty() {
        dismissProgressDialog();
        mTvListEmpty.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void listProfilesSuccess(List<Profile> profiles) {
        dismissProgressDialog();
        this.mProfiles = profiles;

        mTvListEmpty.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new ProfileAdapter(profiles, this));
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}