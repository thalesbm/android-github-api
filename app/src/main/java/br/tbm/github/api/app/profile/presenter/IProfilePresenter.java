package br.tbm.github.api.app.profile.presenter;

import br.tbm.github.api.app.profile.repository.entity.RepositoriesResponse;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.commons.presenter.BasePresenterCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IProfilePresenter extends BasePresenterCallbacks<RepositoriesResponse> {
    void searchPublicRepositoriesInServer(String profileName);
    void updateScreen(Profile profile);
}
