package br.tbm.github.api.app.githubRateLimit.repository;

import br.tbm.github.api.app.githubRateLimit.presenter.IGithubRateLimitPresenter;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IGithubRateLimitRepository {
    void searchRateLimitInServer();
    void setCallback(IGithubRateLimitPresenter presenter);
}
