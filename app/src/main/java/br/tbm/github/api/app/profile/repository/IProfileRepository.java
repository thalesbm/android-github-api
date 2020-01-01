package br.tbm.github.api.app.profile.repository;

import java.util.ArrayList;

import br.tbm.github.api.app.profile.presenter.IProfilePresenter;
import br.tbm.github.api.app.profile.repository.entity.RepositoriesResponse;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IProfileRepository {
    void searchPublicRepositoriesInServer(String profileName);
    void setCallback(IProfilePresenter presenter);
}
