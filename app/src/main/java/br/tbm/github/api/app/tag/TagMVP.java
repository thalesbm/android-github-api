package br.tbm.github.api.app.tag;

import java.util.ArrayList;

import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface TagMVP {

    interface View extends BaseViewCallbacks<BranchesTagsResponse> {
        void listTagsEmpty();
        void listTags(ArrayList<BranchesTagsResponse> tags);
    }

    interface Presenter extends BasePresenterCallbacks<BranchesTagsResponse> {
        void searchTagsInServer(String profileName, String repositoryName);
    }

    interface Model {
        void searchTagsInServer(String profileName, String repositoryName);
        void setCallback(TagMVP.Presenter presenter);
    }
}
