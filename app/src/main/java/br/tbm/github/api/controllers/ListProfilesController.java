package br.tbm.github.api.controllers;

import java.util.ArrayList;
import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.interfaces.ControllerCallbacks;
import br.tbm.github.api.interfaces.TasksCallbacks;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.tasks.ListGithubUsersTask;
import br.tbm.github.api.tasks.RemoveUsersTask;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class ListProfilesController implements
        TasksCallbacks.RemoveUsersTaskCallback,
        TasksCallbacks.ListGithubUserTaskCallback {

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    private final boolean closeActivity = false;

    private ControllerCallbacks<List<Profile>> mCallback;

    public ListProfilesController(ControllerCallbacks<List<Profile>> callback) {
        this.mCallback = callback;
    }

    /**
     * Metodo para listar todos os perfils salvos no app
     */
    public void getProfiles() {
        new ListGithubUsersTask(this).execute();
    }

    /**
     * Metodo responsavel por selecionar apenas os itens que estao marcados para excluir, chamar a task
     * para remover os items e listar novamente
     */
    public void removeItems(List<Profile> profiles) {
        List<Profile> listToRemove = new ArrayList<>();
        for (Profile p : profiles) {
            if (p.hasSelected()) {
                listToRemove.add(p);
            }
        }

        new RemoveUsersTask(this, listToRemove).execute();
    }

    /**
     * Verifica quantos items foram selecionado para atualizar o action mode title
     */
    public String checkNumberOfItemsHasBeenChecked(List<Profile> profiles) {
        int qtd = 0;
        for (Profile p : profiles) {
            if (p.hasSelected()) {
                qtd++;
            }
        }
        return String.valueOf(qtd);
    }

    // ################
    // CALLBACK DA TASK
    // ################

    @Override
    public void listGithubUserTaskSuccess(List<Profile> profiles) {
        mCallback.success(profiles);
    }

    @Override
    public void listGithubUserTaskFailure() {
        mCallback.displayAlertDialog(R.string.generic_database_issue, closeActivity);
    }

    @Override
    public void removeUserTaskSuccess(List<Profile> profiles) {
        mCallback.success(profiles);
    }

    @Override
    public void removeUserTaskFailure() {
        mCallback.displayAlertDialog(R.string.generic_database_issue, closeActivity);
    }
}
