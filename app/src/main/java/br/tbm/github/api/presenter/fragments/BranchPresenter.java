package br.tbm.github.api.presenter.fragments;

import java.util.ArrayList;

import br.tbm.github.api.model.fragments.BranchModel;
import br.tbm.github.api.network.entities.BranchesTagsResponse;
import br.tbm.github.api.interfaces.BranchMVP;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class BranchPresenter extends BasePresenter<BranchesTagsResponse> implements
        BranchMVP.Presenter {

    private BranchMVP.View mView;
    private BranchMVP.Model mModel;

    public BranchPresenter(BranchMVP.View view) {
        this.mView = view;
        this.mModel = new BranchModel(this);

        closeActivity = true;
    }

    /**
     * Metodo para pesquisar as branches de um determinado perfil
     *
     * @param profileName String
     */
    @Override
    public void search(String profileName, String repositoryName) {
        this.mModel.searchInServer(profileName, repositoryName);
    }

    // ######################
    // CALLBACK DO MODEL
    // ######################

    @Override
    public void success(ArrayList<BranchesTagsResponse> response) {
        super.success(response);
        mView.success(response);
    }

    @Override
    public void networkIssue(int code) {
        super.networkIssue(code);
        mView.networkIssue(code, closeActivity);
    }

    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }
}
