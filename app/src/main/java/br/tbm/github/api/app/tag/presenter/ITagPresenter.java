package br.tbm.github.api.app.tag.presenter;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ITagPresenter extends BasePresenterCallbacks<BranchesTagsResponse> {
    void searchTagsInServer(String profileName, String repositoryName);
}
