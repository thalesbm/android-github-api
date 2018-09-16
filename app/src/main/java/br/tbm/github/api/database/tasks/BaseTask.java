package br.tbm.github.api.database.tasks;

import android.os.AsyncTask;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.database.dao.DatabaseHelper;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public abstract class BaseTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected final String TAG = BaseTask.class.getSimpleName();

    protected DatabaseHelper mDatabase;

    public BaseTask() {
        this.mDatabase = GithubApplication.getDataBaseHelper();
    }
}
