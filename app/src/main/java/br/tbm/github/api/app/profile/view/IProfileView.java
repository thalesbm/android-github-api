package br.tbm.github.api.app.profile.view;

import java.util.ArrayList;

import br.tbm.github.api.app.profile.repository.entity.RepositoriesResponse;
import br.tbm.github.api.commons.view.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IProfileView extends BaseViewCallbacks<RepositoriesResponse> {
    void repositoriesListEmpty();
    void displayRepositories(ArrayList<RepositoriesResponse> repositories);
    void downloadProfileImage(String avatarUrl);
    void updateToolbarTitle(String name);
}
