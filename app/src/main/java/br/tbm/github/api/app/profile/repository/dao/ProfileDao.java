package br.tbm.github.api.app.profile.repository.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.commons.repository.dao.DatabaseHelper;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class ProfileDao extends BaseDaoImpl<Profile, Long> {

    public ProfileDao(ConnectionSource connectionSource, Class<Profile> dataClass, DatabaseHelper dbHelper) throws SQLException {
        super(connectionSource, dataClass);
    }

    /**
     * Metodo retorna todos os perfils salvos por ordem do mais recente para o mais antigo
     *
     * @return List<Profile>
     * @throws SQLException
     */
    public List<Profile> listProfiles() throws SQLException {
        QueryBuilder<Profile, Long> queryBuilder = queryBuilder();
        queryBuilder.orderBy("created", false);
        return queryBuilder.query();
    }
}
