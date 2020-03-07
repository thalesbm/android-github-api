package br.tbm.github.api.commons;

import android.app.Application;

import br.tbm.github.api.commons.network.RestAPI;
import br.tbm.github.api.commons.repository.dao.DatabaseHelper;
import retrofit2.Retrofit;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class GithubApplication extends Application {

    private static DatabaseHelper dataBaseHelper = null;

    private static Retrofit retrofitInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();

        if (dataBaseHelper == null) {
            dataBaseHelper = DatabaseHelper.getHelper(this);
        }

        if (retrofitInstance == null) {
            retrofitInstance = RestAPI.getRetrofitInstance();
        }

        getDataBaseHelper().getWritableDatabase();
    }

    public static DatabaseHelper getDataBaseHelper() {
        return dataBaseHelper;
    }

    public static Retrofit getRetrofitInstance() {
        return retrofitInstance;
    }
}
