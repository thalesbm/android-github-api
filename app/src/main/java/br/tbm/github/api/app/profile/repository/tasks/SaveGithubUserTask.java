package br.tbm.github.api.app.profile.repository.tasks;

import android.util.Log;

import java.sql.SQLException;
import java.util.Date;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.repository.tasks.BaseTask;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class SaveGithubUserTask extends BaseTask<Profile, Void, Profile> {

    private TasksCallbacks.SaveGithubUserTaskCallback mCallback;

    public SaveGithubUserTask(TasksCallbacks.SaveGithubUserTaskCallback callback) {
        this.mCallback = callback;
    }

    @Override
    protected Profile doInBackground(Profile... params) {
        Profile profile = params[0];
        try {
            profile.setCreated(new Date());

            mDatabase.getProfileDao().create(profile);
        } catch (SQLException e) {
            Log.e(TAG, "Ocorreu um erro ao salvar o usuario do github");
            e.printStackTrace();
            mCallback.saveGithubUserTaskFailure();
        }
        return profile;
    }

    @Override
    protected void onPostExecute(Profile profile) {
        super.onPostExecute(profile);
        mCallback.saveGithubUserTaskSuccess(profile);
    }
}