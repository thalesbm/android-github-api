package br.tbm.github.api.app.githubRateLimit.view;

import br.tbm.github.api.app.githubRateLimit.repository.entity.ResourcesResponse;
import br.tbm.github.api.commons.view.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IGithubRateLimitView extends BaseViewCallbacks<ResourcesResponse> {
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
