package br.tbm.github.api.app.profile.repository.tasks;

import android.util.Log;

import java.sql.SQLException;
import java.util.List;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.commons.repository.tasks.BaseTask;

/**
 * Created by thalesbertolini on 25/08/2018
 **/
public class RemoveUsersTask extends BaseTask<Void, Void, List<Profile>> {

    private TasksCallbacks.RemoveUsersTaskCallback mCallback;
    private List<Profile> mProfiles;

    public RemoveUsersTask(TasksCallbacks.RemoveUsersTaskCallback callback, List<Profile> profiles) {
        this.mCallback = callback;
        this.mProfiles = profiles;
    }

    @Override
    protected List<Profile> doInBackground(Void... voids) {
        try {
            mDatabase.getProfileDao().delete(mProfiles);

            mProfiles = mDatabase.getProfileDao().queryForAll();
        } catch (SQLException e) {
            Log.e(TAG, "Ocorreu um erro ao remover os usuarios do github");
            e.printStackTrace();
            mCallback.removeUserTaskFailure();
        }

        return mProfiles;
    }

    @Override
    protected void onPostExecute(List<Profile> list) {
        super.onPostExecute(list);
        mCallback.removeUserTaskSuccess(list);
    }
}
