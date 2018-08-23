package br.tbm.github.api.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.tbm.github.api.models.Historic;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class HistoricDao extends BaseDaoImpl<Historic, Long> {

    public HistoricDao(ConnectionSource connectionSource, Class<Historic> dataClass, DatabaseHelper dbHelper) throws SQLException {
        super(connectionSource, dataClass);
    }
}
