package br.tbm.github.api.app.githubRateLimit;

import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;
import br.tbm.github.api.app.githubRateLimit.repository.entity.ResourcesResponse;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface GithubRateLimitMVP {

    interface View extends BaseViewCallbacks<ResourcesResponse> {
        void setCoreLimit(int message, int limit);
        void setCoreRemaining(int message, int remaining);
        void hideCoreFields();

        void setSearchLimit(int message, int limit);
        void setSearchRemaining(int message, int remaining);
        void hideSearchFields();

        void setGraphLimit(int message, int limit);
        void setGraphRemaining(int message, int remaining);
        void hideGraphFields();

        void hideProgressDialog();
    }

    interface Presenter extends BasePresenterCallbacks<ResourcesResponse> {
        void searchRateLimitInServer();
    }

    interface Model {
        void searchRateLimitInServer();
        void setCallback(GithubRateLimitMVP.Presenter presenter);
    }
}
