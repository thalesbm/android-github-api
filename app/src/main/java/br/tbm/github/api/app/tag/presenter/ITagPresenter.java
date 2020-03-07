package br.tbm.github.api.app.tag.presenter;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.commons.presenter.BasePresenterCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ITagPresenter extends BasePresenterCallbacks<BranchesTagsResponse> {
    void searchTagsInServer(String profileName, String repositoryName);
}
