package br.tbm.github.api.app.commitDetails.repository;

import br.tbm.github.api.app.commitDetails.presenter.ICommitDetailsPresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ICommitDetailsRepository {
    void searchCommitDetailsInServer(String username, String repositoryName, String sha, ICommitDetailsPresenter commitsDetailsPresenter);

}
