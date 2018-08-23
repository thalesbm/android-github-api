package br.tbm.github.api;

import android.app.Application;

import br.tbm.github.api.dao.DatabaseHelper;

public class GithubApplication extends Application {

    private static DatabaseHelper dataBaseHelper = null;

    @Override
    public void onCreate() {
        super.onCreate();

        if (dataBaseHelper == null) {
            dataBaseHelper = DatabaseHelper.getHelper(this);
        }

        getDataBaseHelper().getWritableDatabase();
    }

    public static DatabaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }
}
