package br.tbm.github.api.app.branch.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.tbm.github.api.app.branch.BranchMVP;
import br.tbm.github.api.app.branch.presenter.BranchPresenter;
import br.tbm.github.api.app.profile.repository.model.Profile;
import br.tbm.github.api.app.searchByUsername.SearchByUsernameMVP;
import br.tbm.github.api.app.searchByUsername.presenter.SearchByUsernamePresenter;
import br.tbm.github.api.shared.network.RestRepository;
import br.tbm.github.api.shared.repository.BaseTestsRepository;
import br.tbm.github.api.shared.repository.MockRestRepositorySuccess;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;

import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BranchRepositoryTest extends BaseTestsRepository {

    @Mock
    BranchMVP.View mView;

    @Mock
    BranchMVP.Model mModel;

    private BranchPresenter mPresenter;
    private BranchRepository mRepository;

    @Before
    public void setUp() {
        super.setUp();
        mPresenter = new BranchPresenter(mView, mModel);
        mRepository = new BranchRepository();
    }

    @Test
    public void searchBranchesInServer_Success_Test() throws IOException {
        BehaviorDelegate<RestRepository> delegate = mockRetrofit.create(RestRepository.class);
        RestRepository mockRepository = new MockRestRepositorySuccess(delegate);

        Call<ArrayList<BranchesTagsResponse>> quote = mockRepository.listBranches("thalesbm", "android-github-api");
        Response<ArrayList<BranchesTagsResponse>> response = quote.execute();

        assertTrue(response.isSuccessful());
        assertEquals("android-github-api", response.body().get(0).getName());
    }

    @Test
    public void test1() {
        doAnswer(invocation -> {
            BranchPresenter callback = invocation.getArgument(0);
            callback.success(new ArrayList<>());
            return null;

        }).when(mModel).searchBranchesInServer("1", "2");

        mRepository.searchBranchesInServer("1", "2");

//        doAnswer(invocation -> {
//            BranchPresenter callback = invocation.getArgument(0);
//            callback.success(new ArrayList<>());
//            return null;
//
//        }).when(mModel).searchBranchesInServer("1", "2");
//        mPresenter.searchBranchesInServer("1", "2");
    }

//    @Test
//    public void searchBranchesInServer_Error_Test() throws IOException {
//        BehaviorDelegate<RestRepository> delegate = mockRetrofit.create(RestRepository.class);
//        RestRepository mockRepository = new MockRestRepositoryError(delegate);
//
//        Call<ArrayList<BranchesTagsResponse>> quote = mockRepository.listBranches("thalesbm", "android-github-api");
//        Response<ArrayList<BranchesTagsResponse>> response = quote.execute();
//
//        assertFalse(response.isSuccessful());
//
////        Converter<ResponseBody, BranchesTagsResponse> errorConverter = retrofit.responseBodyConverter(BranchesTagsResponse.class, new Annotation[0]);
////        BranchesTagsResponse error = errorConverter.convert(quoteOfTheDayResponse.errorBody());
//
//        assertEquals("android-github-api", response.code());
//    }
}