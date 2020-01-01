package br.tbm.github.api.app.commitDetails.presenter;

import java.util.List;

import br.tbm.github.api.app.commitDetails.repository.entity.CommitFilesResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ICommitDetailsPresenter extends BasePresenterCallbacks<CommitsResponse> {
    void searchCommitDetailsInServer(String username, String repositoryName, String sha);
}
