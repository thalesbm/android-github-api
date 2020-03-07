package br.tbm.github.api.commons.repository.tasks;

import android.os.AsyncTask;

import br.tbm.github.api.commons.GithubApplication;
import br.tbm.github.api.commons.repository.dao.DatabaseHelper;

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
