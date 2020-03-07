package br.tbm.github.api.app.commitDetails.presenter;

import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.commons.presenter.BasePresenterCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ICommitDetailsPresenter extends BasePresenterCallbacks<CommitsResponse> {
    void searchCommitDetailsInServer(String username, String repositoryName, String sha);
}
