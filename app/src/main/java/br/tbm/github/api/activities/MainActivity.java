package br.tbm.github.api.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.adapters.ProfileAdapter;
import br.tbm.github.api.interfaces.AdaptersCallbacks;
import br.tbm.github.api.interfaces.TasksCallbacks;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.tasks.ListGithubUsersTask;
import br.tbm.github.api.utils.RedirectUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TextView mTvListEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar(findViewById(R.id.toolbar));
        changeToolbarTitle(getString(R.string.main_activity_toolbar));

//        getToolbar().startActionMode(test);

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
//        registerForContextMenu(mRecyclerView);
//
//
//        mRecyclerView.setOnLongClickListener((AdapterView<?> parent, View view, int position, long id) -> {
//
//                }
//
//        mRecyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("Long Click");
//            }
//        });
//
//        mRecyclerView.setOnContextClickListener(new View.OnContextClickListener() {
//            @Override
//            public boolean onContextClick(View v) {
//                return false;
//            }
//        });

//        mRecyclerView.setOnLongClickListener((View v) -> {
//            System.out.println("Long Click");
//
//            v.setSelected(true);
//            return true;
//        });

//                  });
//
//public boolean onItemLongClick (AdapterView parent, View view, int position, long id) {
//                System.out.println("Long click");
//                // startActionMode(modeCallBack);
//                view.setSelected(true);
//                return true;
//            }
//
//
//        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, mRecyclerView, new RecyclerClick_Listener() {
//            @Override
//            public void onClick(View view, int position) {
//                //If ActionMode not null select item
////                if (mActionMode != null)
////                    onListItemSelect(position);
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//                //Select item on long click
//                onListItemSelect(position);
//            }
//        }));
//    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {
//
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            mode.setTitle("Options");
//            mode.getMenuInflater().inflate(R.menu.menu_list, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            return false;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//
//        }
//    };
//
//    private void onListItemSelect(int position) {
//        mRecyclerView.toggleSelection(position);//Toggle the selection
//
//        boolean hasCheckedItems = mRecyclerView.getSelectedCount() > 0;//Check if any items are already selected or not
//
//
//        if (hasCheckedItems && mActionMode == null)
//            // there are some selected items, start the actionMode
//            mActionMode = ((AppCompatActivity) this).startSupportActionMode(new Toolbar_ActionMode_Callback(getActivity(),adapter, null, item_models, false));
//        else if (!hasCheckedItems && mActionMode != null)
//            // there no selected items, finish the actionMode
//            mActionMode.finish();
//
//        if (mActionMode != null)
//            //set action mode title on item selection
//            mActionMode.setTitle(String.valueOf(adapter
//                    .getSelectedCount()) + " selected");
//
//
//    }

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

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        if (v.getId() == R.id.main_activity_list_description_text_view) {
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.menu_list, menu);
//        }
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId()) {
//            case R.id.add:
//                // add stuff here
//                return true;
//            case R.id.edit:
//                // edit stuff here
//                return true;
//            case R.id.delete:
//                // remove stuff here
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }

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
        dismissProgressDialog();
        if (profiles.isEmpty()) {
            mTvListEmpty.setVisibility(View.VISIBLE);
        } else {
            mTvListEmpty.setVisibility(View.GONE);
            mRecyclerView.setAdapter(new ProfileAdapter(profiles, new AdaptersCallbacks.ProfileAdapterCallback() {
                @Override
                public void longClick(int position) {
                    startSupportActionMode(test);
                   // startActionMode(test2);
                }
            }));
        }
    }
//
//    private ActionMode.Callback test2 = new ActionMode.Callback() {
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            mode.setTitle("Options");
//            // getToolbar().hide();
//            mode.getMenuInflater().inflate(R.menu.menu_list, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//
//        @Override
//        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//            int id = item.getItemId();
//            switch (id) {
//                case R.id.delete: {
//                    mode.finish();
//                    break;
//                }
//                case R.id.edit: {
//                    System.out.println(" edit ");
//                    break;
//                }
//                default:
//                    return false;
//
//            }
//            return true;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//
//        }
//    };

    private android.support.v7.view.ActionMode.Callback test = new android.support.v7.view.ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
            mode.setTitle("Options");
           // getToolbar().hide();
            mode.getMenuInflater().inflate(R.menu.menu_list, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
//            getToolbar().hide();
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.delete: {
                    mode.finish();
                    break;
                }
                case R.id.edit: {
                    System.out.println(" edit ");
                    break;
                }
                default:
                    return false;

            }
            return true;
        }

        @Override
        public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {
//            getToolbar().show();
        }
    };
}