package br.tbm.github.api.app.githubRateLimit.repository;

import br.tbm.github.api.app.githubRateLimit.presenter.IGithubRateLimitPresenter;
import br.tbm.github.api.app.githubRateLimit.repository.entity.ResourcesResponse;
import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IGithubRateLimitRepository {
    void searchRateLimitInServer();
    void setCallback(IGithubRateLimitPresenter presenter);
}
