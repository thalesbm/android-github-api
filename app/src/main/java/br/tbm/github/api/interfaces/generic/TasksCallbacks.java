package br.tbm.github.api.interfaces.generic;

import java.util.List;

import br.tbm.github.api.database.data.Profile;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public interface TasksCallbacks {

    /**
     * Callback da task de salvar usuario
     */
    interface SaveGithubUserTaskCallback {
        void saveGithubUserTaskSuccess(Profile profile);

        void saveGithubUserTaskFailure();
    }

    /**
     * Callback da task de listar usuarios
     */
    interface ListGithubUserTaskCallback {
        void listGithubUserTaskSuccess(List<Profile> profiles);

        void listGithubUserTaskFailure();
    }

    /**
     * Callback da task de remover usuarios
     */
    interface RemoveUsersTaskCallback {
        void removeUserTaskSuccess(List<Profile> profiles);

        void removeUserTaskFailure();
    }
}
