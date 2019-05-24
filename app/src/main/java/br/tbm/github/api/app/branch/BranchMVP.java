package br.tbm.github.api.app.branch;

import java.util.ArrayList;

import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;
import retrofit2.Retrofit;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface BranchMVP {

    interface View extends BaseViewCallbacks<BranchesTagsResponse> {
        void branchesListEmpty();
        void branchesList(ArrayList<BranchesTagsResponse> branches);
    }

    interface Presenter extends BasePresenterCallbacks<BranchesTagsResponse> {
        void searchBranchesInServer(String profileName, String repositoryName);
    }

    interface Model {
        void searchBranchesInServer(String profileName, String repositoryName);
        void setCallback(Presenter presenter, Retrofit retrofit);
    }
}
