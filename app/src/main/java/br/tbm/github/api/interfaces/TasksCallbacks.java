package br.tbm.github.api.interfaces;

import java.util.List;

import br.tbm.github.api.models.Profile;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public interface TasksCallbacks {

    /**
     * Interface para retornar da task para a activity o status do profile
     */
    interface SaveGithubUserTaskCallback {
        void saveGithubUserTaskSuccess();

        void saveGithubUserTaskFailure();
    }

    /**
     * Interface para retornar da task para a activity a lista the todos os profiles pesquisados
     */
    interface ListGithubUserTaskCallback {
        void listGithubUserTaskSuccess(List<Profile> profiles);

        void listGithubUserTaskFailure();
    }
}
