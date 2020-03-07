package br.tbm.github.api.app.listProfiles.presenter;

import java.util.ArrayList;
import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.app.listProfiles.repository.IListProfilesRepository;
import br.tbm.github.api.app.listProfiles.view.IListProfilesView;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.commons.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class ListProfilesPresenter extends BasePresenter<List<Profile>> implements
        IListProfilesPresenter {

    private IListProfilesView mView;
    private IListProfilesRepository mModel;

    public ListProfilesPresenter(IListProfilesView view, IListProfilesRepository model) {
        super();
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);
    }

    @Override
    public void needsToCloseCurrentActivity() {
        closeActivity = false;
    }

    @Override
    public void getProfilesInDatabase() {
        mView.updateProgressDialog(R.string.loading);
        mModel.listProfilesInDatabase();
    }

    /**
     * Metodo responsavel por selecionar apenas os itens que estao marcados para excluir, chamar a task
     * para remover os items e listar novamente
     */
    @Override
    public void removeItems(List<Profile> profiles) {
        List<Profile> listToRemove = new ArrayList<>();
        for (Profile p : profiles) {
            if (p.hasSelected()) {
                listToRemove.add(p);
            }
        }

        mModel.removeProfilesFromDatabase(listToRemove);
    }

    /**
     * Verifica quantos items foram selecionado para atualizar o action mode title
     */
    @Override
    public String checkNumberOfItemsHasBeenChecked(List<Profile> profiles) {
        int qtd = 0;
        for (Profile p : profiles) {
            if (p.hasSelected()) {
                qtd++;
            }
        }
        return String.valueOf(qtd);
    }

    // ######################
    // CALLBACK DO REPOSITORY
    // ######################

    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }

    @Override
    public void success(List<Profile> profiles) {
        super.success(profiles);

        if (profiles.isEmpty()) {
            mView.listProfilesEmpty();
        } else {
            mView.listProfilesSuccess(profiles);
        }
    }
}
