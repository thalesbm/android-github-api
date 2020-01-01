package br.tbm.github.api.app.tag.repository;

import br.tbm.github.api.app.tag.presenter.ITagPresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ITagRepository {
    void searchTagsInServer(String profileName, String repositoryName);
    void setCallback(ITagPresenter presenter);
}

