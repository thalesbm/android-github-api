package br.tbm.github.api.app.tag.presenter;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.tag.repository.ITagRepository;
import br.tbm.github.api.app.tag.view.ITagView;
import br.tbm.github.api.shared.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class TagPresenter extends BasePresenter<BranchesTagsResponse>
        implements ITagPresenter {

    private ITagView mView;
    private ITagRepository mModel;

    public TagPresenter(ITagView view, ITagRepository model) {
        super();
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);
    }

    @Override
    public void needsToCloseCurrentActivity() {
        closeActivity = true;
    }

    @Override
    public void searchTagsInServer(String profileName, String repositoryName) {
        mView.updateProgressDialog(R.string.loading);
        mModel.searchTagsInServer(profileName, repositoryName);
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
    public void success(ArrayList<BranchesTagsResponse> response) {
        super.success(response);

        if (response.isEmpty()) {
            mView.listTagsEmpty();
        } else {
            mView.listTags(response);
        }
    }

    @Override
    public void networkIssue(int code) {
        super.networkIssue(code);
        mView.networkIssue(code, closeActivity);
    }
}
