package br.tbm.github.api.database.tasks;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.tbm.github.api.database.data.Profile;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class ListGithubUsersTask extends BaseTask<Void, Void, List<Profile>> {

    private TasksCallbacks.ListGithubUserTaskCallback mCallback;

    public ListGithubUsersTask(TasksCallbacks.ListGithubUserTaskCallback callback) {
        this.mCallback = callback;
    }

    @Override
    protected List<Profile> doInBackground(Void... voids) {
        List<Profile> profiles = new ArrayList<>();
        try {
            profiles = mDatabase.getProfileDao().listProfiles();
        } catch (SQLException e) {
            Log.e(TAG, "Ocorreu um erro ao listar os profiles do github");
            e.printStackTrace();
            mCallback.listGithubUserTaskFailure();
        }
        return profiles;
    }

    @Override
    protected void onPostExecute(List<Profile> profiles) {
        super.onPostExecute(profiles);
        mCallback.listGithubUserTaskSuccess(profiles);
    }
}