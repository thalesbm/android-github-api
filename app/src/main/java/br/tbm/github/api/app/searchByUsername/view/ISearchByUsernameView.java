package br.tbm.github.api.app.searchByUsername.view;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.commons.view.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ISearchByUsernameView extends BaseViewCallbacks<Profile> {
    void validateNotPassed(int message);
    void validatePassed();
}
