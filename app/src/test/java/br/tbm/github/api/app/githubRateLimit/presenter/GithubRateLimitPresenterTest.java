package br.tbm.github.api.app.githubRateLimit.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.tbm.github.api.R;
import br.tbm.github.api.app.githubRateLimit.repository.entity.RateLimitItemsResponse;
import br.tbm.github.api.app.githubRateLimit.repository.entity.RateLimitResponse;
import br.tbm.github.api.app.githubRateLimit.repository.entity.ResourcesResponse;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GithubRateLimitPresenterTest {

    @Mock
    GithubRateLimitMVP.View mView;

    @Mock
    GithubRateLimitMVP.Model mModel;

    private GithubRateLimitPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new GithubRateLimitPresenter(mView, mModel);
    }

    @Test
    public void searchRateLimitInServer_Test() {
        mPresenter.searchRateLimitInServer();
        verify(mView, atLeastOnce()).updateProgressDialog(R.string.loading);
        verify(mModel, atLeastOnce()).searchRateLimitInServer();
    }

    @Test
    public void success_EmptyResponse_Test() {
        mPresenter.success(createEmptyResourcesResponse());
        verify(mView, atLeastOnce()).hideProgressDialog();
        verify(mView, atLeastOnce()).hideCoreFields();
        verify(mView, atLeastOnce()).hideSearchFields();
        verify(mView, atLeastOnce()).hideGraphFields();
    }

    @Test
    public void success_Test() {
        mPresenter.success(createResourcesResponse());
        verify(mView, atLeastOnce()).hideProgressDialog();
        verify(mView, atLeastOnce()).setCoreLimit(anyInt(), anyInt());
        verify(mView, atLeastOnce()).setCoreRemaining(anyInt(), anyInt());
        verify(mView, atLeastOnce()).setGraphLimit(anyInt(), anyInt());
        verify(mView, atLeastOnce()).setGraphRemaining(anyInt(), anyInt());
        verify(mView, atLeastOnce()).setSearchLimit(anyInt(), anyInt());
        verify(mView, atLeastOnce()).setSearchRemaining(anyInt(), anyInt());
    }

    @Test
    public void networkIssue_Test() {
        mPresenter.networkIssue(500);
        verify(mView, atLeastOnce()).networkIssue(500, true);
    }

    @Test
    public void displayAlertDialog_Test() {
        mPresenter.displayAlertDialog(500);
        verify(mView, atLeastOnce()).displayAlertDialog(500, true);
    }

    private ResourcesResponse createEmptyResourcesResponse() {
        ResourcesResponse response = new ResourcesResponse();
        RateLimitResponse rateLimitResponse = new RateLimitResponse();
        response.setRateLimitResponse(rateLimitResponse);
        return response;
    }

    private ResourcesResponse createResourcesResponse() {
        ResourcesResponse response = new ResourcesResponse();
        RateLimitResponse rateLimitResponse = new RateLimitResponse();
        rateLimitResponse.setCore(new RateLimitItemsResponse());
        rateLimitResponse.setSearch(new RateLimitItemsResponse());
        rateLimitResponse.setGraphql(new RateLimitItemsResponse());
        response.setRateLimitResponse(rateLimitResponse);
        return response;
    }
}