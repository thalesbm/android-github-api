package br.tbm.github.api.app.branch.repository;

import br.tbm.github.api.app.branch.presenter.IBranchPresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IBranchRepository {
    void searchBranchesInServer(String profileName, String repositoryName, IBranchPresenter callback);
}
