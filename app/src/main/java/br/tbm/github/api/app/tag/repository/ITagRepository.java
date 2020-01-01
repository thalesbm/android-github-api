package br.tbm.github.api.app.tag.repository;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.tag.presenter.ITagPresenter;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ITagRepository {
    void searchTagsInServer(String profileName, String repositoryName);
    void setCallback(ITagPresenter presenter);
}

