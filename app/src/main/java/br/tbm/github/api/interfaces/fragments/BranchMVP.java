package br.tbm.github.api.interfaces.fragments;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.network.entities.BranchesTagsResponse;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface BranchMVP {

    interface View extends BaseViewCallbacks<BranchesTagsResponse> {

    }

    interface Presenter extends BasePresenterCallbacks<BranchesTagsResponse> {
        void search(String profileName, String repositoryName);
    }

    interface Model {
        void searchInServer(String profileName, String repositoryName);
    }
}
