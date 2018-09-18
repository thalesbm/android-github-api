package br.tbm.github.api.interfaces;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;
import br.tbm.github.api.network.entities.ResourcesResponse;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface GithubRateLimitMVP {

    interface View extends BaseViewCallbacks<ResourcesResponse> {

    }

    interface Presenter extends BasePresenterCallbacks<ResourcesResponse> {
        void search();
    }

    interface Model {
        void searchInServer();

        void setCallback(GithubRateLimitMVP.Presenter presenter);
    }
}
