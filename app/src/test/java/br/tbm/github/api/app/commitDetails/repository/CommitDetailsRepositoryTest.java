package br.tbm.github.api.app.commitDetails.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.TimeUnit;

import br.tbm.github.api.app.commitDetails.presenter.CommitsDetailsPresenter;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.shared.GithubApiUtils;
import br.tbm.github.api.shared.repository.BaseTestsRepository;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CommitDetailsRepositoryTest extends BaseTestsRepository {

    @Mock
    CommitDetailsMVP.View mView;

    @Mock
    CommitDetailsMVP.Model mModel;

    private CommitDetailsMVP.Presenter mPresenter;

    @Before
    public void setUp() {
        super.setUp();
        mPresenter = new CommitsDetailsPresenter(mView, mModel);
    }

    @Test
    public void test4() {
        NetworkBehavior behavior = NetworkBehavior.create();
        behavior.setDelay(20, TimeUnit.SECONDS);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/repos/" + username
                + "/" + repository + "/commits/" + sha + "/").client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).build();

        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(behavior).build();

        CommitDetailsMVP.Model model = new CommitDetailsRepository(mockRetrofit.retrofit());
        model.searchCommitDetailsInServer(username, repository, sha, mPresenter);

        CommitDetailsMVP.Presenter presenter = mock(CommitDetailsMVP.Presenter.class);

        verify(presenter).displayAlertDialog(400);
    }

    @Test
    public void searchCommitDetailsInServer_Success_Test() {
        CommitsResponse response = GithubApiUtils.getCommitsResponse();
        doAnswer(invocation -> {
            CommitDetailsMVP.Presenter callback = invocation.getArgument(3);
            callback.success(response);
            return null;

        }).when(mModel).searchCommitDetailsInServer(username, repository, sha, mPresenter);

        mPresenter.searchCommitDetailsInServer(username, repository, sha);

        verify(mView, atLeastOnce()).setCommitterName(response.getOwnerResponse().getLogin());
    }

    @Test
    public void searchCommitDetailsInServer_networkIssue_Test() {
        doAnswer(invocation -> {
            CommitDetailsMVP.Presenter callback = invocation.getArgument(3);
            callback.networkIssue(400);
            return null;

        }).when(mModel).searchCommitDetailsInServer(username, repository, sha, mPresenter);

        mPresenter.searchCommitDetailsInServer(username, repository, sha);

        verify(mView, atLeastOnce()).networkIssue(400, true);
    }

    @Test
    public void searchCommitDetailsInServer_displayAlertDialog_Test() {
        doAnswer(invocation -> {
            CommitDetailsMVP.Presenter callback = invocation.getArgument(3);
            callback.displayAlertDialog(400);
            return null;

        }).when(mModel).searchCommitDetailsInServer(username, repository, sha, mPresenter);

        mPresenter.searchCommitDetailsInServer(username, repository, sha);

        verify(mView, atLeastOnce()).displayAlertDialog(400, true);
    }
}