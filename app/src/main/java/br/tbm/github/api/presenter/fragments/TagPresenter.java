package br.tbm.github.api.presenter.fragments;

import java.util.ArrayList;

import br.tbm.github.api.repository.fragments.TagRepository;
import br.tbm.github.api.network.entities.BranchesTagsResponse;
import br.tbm.github.api.interfaces.fragments.TagMVP;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class TagPresenter extends BasePresenter<BranchesTagsResponse>
        implements TagMVP.Presenter {

    private TagMVP.View mView;
    private TagMVP.Model mModel;

    public TagPresenter(TagMVP.View view, TagMVP.Model model) {
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);

        closeActivity = true;
    }

    /**
     * Metodo para pesquisar as tags de um determinado perfil
     *
     * @param profileName String
     */
    @Override
    public void search(String profileName, String repositoryName) {
        mModel.searchInServer(profileName, repositoryName);
    }

    // ######################
    // CALLBACK DO MODEL
    // ######################

    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }

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
}
