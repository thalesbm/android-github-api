package br.tbm.github.api.presenter.activities;

import br.tbm.github.api.model.activities.CommitDetailsModel;
import br.tbm.github.api.network.entities.CommitsResponse;
import br.tbm.github.api.interfaces.activities.CommitDetailsMVP;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class CommitsDetailsPresenter extends BasePresenter<CommitsResponse> implements
        CommitDetailsMVP.Presenter {

    private CommitDetailsMVP.View mView;
    private CommitDetailsMVP.Model mModel;

    public CommitsDetailsPresenter(CommitDetailsMVP.View view) {
        this.mView = view;
        this.mModel = new CommitDetailsModel(this);

        closeActivity = true;
    }

    /**
     * Metodo para pesquisar todos os commits de um especifico repositorio
     *
     * @param username       String
     * @param repositoryName String
     * @param sha            String
     */
    @Override
    public void search(String username, String repositoryName, String sha) {
        mModel.searchInServer(username, repositoryName, sha);
    }

    // ######################
    // CALLBACK DO MODEL
    // ######################

    @Override
    public void success(CommitsResponse commitsResponses) {
        super.success(commitsResponses);
        mView.success(commitsResponses);
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
