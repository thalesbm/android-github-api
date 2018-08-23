package br.tbm.github.api.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

import br.tbm.github.api.models.Historic;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "tbm_github_api.db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper databaseHelper = null;
    private static final AtomicInteger usageCounter = new AtomicInteger(0);

    private HistoricDao historicDao = null;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context);
        }
        usageCounter.incrementAndGet();
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Historic.class);

            Log.i(TAG, "Create table with success!");
        } catch (SQLException e) {
            Log.e(TAG, "Error to create table: " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Historic.class, true);

            TableUtils.createTableIfNotExists(connectionSource, Historic.class);

        } catch (Exception e) {
            Log.e(TAG, "Error to update table: " + e);
            throw new RuntimeException(e);
        }
    }

    public HistoricDao getHistoricDao() throws SQLException {
        return historicDao == null ? new HistoricDao(connectionSource, Historic.class, databaseHelper) : historicDao;
    }

    @Override
    public void close() {
        super.close();
        historicDao = null;
        databaseHelper = null;
    }

    public void cleanTable() throws SQLException {
        TableUtils.clearTable(connectionSource, Historic.class);
    }
}
