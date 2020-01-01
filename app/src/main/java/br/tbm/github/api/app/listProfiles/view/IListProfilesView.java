package br.tbm.github.api.app.listProfiles.view;

import java.util.List;

import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IListProfilesView extends BaseViewCallbacks<List<Profile>> {
    void listProfilesSuccess(List<Profile> profiles);
    void listProfilesEmpty();
}
