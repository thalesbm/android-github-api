package br.tbm.github.api.interfaces;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;
import br.tbm.github.api.network.entities.BranchesTagsResponse;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface TagMVP {

    interface View extends BaseViewCallbacks<BranchesTagsResponse> {

    }

    interface Presenter extends BasePresenterCallbacks<BranchesTagsResponse> {
        void search(String profileName, String repositoryName);
    }

    interface Model {
        void searchInServer(String profileName, String repositoryName);

        void setCallback(TagMVP.Presenter presenter);
    }
}
