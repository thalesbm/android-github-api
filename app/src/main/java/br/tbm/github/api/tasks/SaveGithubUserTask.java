package br.tbm.github.api.tasks;

import android.util.Log;

import java.sql.SQLException;
import java.util.Date;

import br.tbm.github.api.models.Profile;
import br.tbm.github.api.interfaces.TasksCallbacks;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class SaveGithubUserTask extends BaseTask<Profile, Void, Void> {

    private TasksCallbacks.SaveGithubUserTaskCallback mCallback;

    public SaveGithubUserTask(TasksCallbacks.SaveGithubUserTaskCallback callback) {
        this.mCallback = callback;
    }

    @Override
    protected Void doInBackground(Profile... params) {
        try {
            Profile profile = params[0];
            profile.setCreated(new Date());

            mDatabase.getProfileDao().create(profile);
        } catch (SQLException e) {
            Log.e(TAG, "Ocorreu um erro ao salvar o usuario do github");
            e.printStackTrace();
            mCallback.saveGithubUserTaskFailure();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mCallback.saveGithubUserTaskSuccess();
    }
}