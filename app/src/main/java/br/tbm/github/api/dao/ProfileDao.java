package br.tbm.github.api.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import br.tbm.github.api.models.Profile;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class ProfileDao extends BaseDaoImpl<Profile, Long> {

    public ProfileDao(ConnectionSource connectionSource, Class<Profile> dataClass, DatabaseHelper dbHelper) throws SQLException {
        super(connectionSource, dataClass);
    }
}
