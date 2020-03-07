package br.tbm.github.api.app.branch.presenter;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.commons.presenter.BasePresenterCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IBranchPresenter extends BasePresenterCallbacks<BranchesTagsResponse> {

    void searchBranchesInServer(String profileName, String repositoryName);

}
