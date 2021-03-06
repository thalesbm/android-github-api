package br.tbm.github.api.app.branch.presenter;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.app.branch.repository.IBranchRepository;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.branch.view.IBranchView;
import br.tbm.github.api.commons.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class BranchPresenter extends BasePresenter<BranchesTagsResponse> implements
        IBranchPresenter {

    private IBranchView mView;
    private IBranchRepository mModel;

    public BranchPresenter(IBranchView view, IBranchRepository model) {
        super();
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void needsToCloseCurrentActivity() {
        closeActivity = true;
    }

    @Override
    public void searchBranchesInServer(String profileName, String repositoryName) {
        mView.updateProgressDialog(R.string.loading);
        mModel.searchBranchesInServer(profileName, repositoryName, this);
    }

    // ######################
    // CALLBACK DO REPOSITORY
    // ######################

    @Override
    public void success(ArrayList<BranchesTagsResponse> response) {
        super.success(response);

        if (response.isEmpty()) {
            mView.branchesListEmpty();
        } else {
            mView.branchesList(response);
        }
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
