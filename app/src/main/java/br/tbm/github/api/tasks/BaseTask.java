package br.tbm.github.api.tasks;

import android.os.AsyncTask;

import br.tbm.github.api.dao.DatabaseHelper;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public abstract class BaseTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

    protected DatabaseHelper mDatabase;

    public BaseTask() {

    }
}
