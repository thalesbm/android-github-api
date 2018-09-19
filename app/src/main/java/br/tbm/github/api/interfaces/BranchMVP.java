package br.tbm.github.api.interfaces;

import java.util.ArrayList;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.network.entities.BranchesTagsResponse;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;

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
        void setCallback(BranchMVP.Presenter presenter);
    }
}
