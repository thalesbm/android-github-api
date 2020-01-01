package br.tbm.github.api.app.githubRateLimit.presenter;

import br.tbm.github.api.app.githubRateLimit.repository.entity.ResourcesResponse;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IGithubRateLimitPresenter extends BasePresenterCallbacks<ResourcesResponse> {
    void searchRateLimitInServer();
}
