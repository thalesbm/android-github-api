package br.tbm.github.api.app.branch.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
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
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.mock.BehaviorDelegate;

import java.io.IOException;
import java.security.Provider;
import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BranchRepositoryTest extends BaseTestsRepository {

    @Mock
    BranchMVP.Presenter mPresenter;

    @Mock
    BranchMVP.Model mModel;

    //    private BranchPresenter mPresenter;
    private BranchRepository mRepository;

    @Before
    public void setUp() {
        super.setUp();
//        mPresenter = new BranchPresenter(mView, mModel);
        mRepository = new BranchRepository();
        mRepository.setCallback(mPresenter, retrofit);
    }

//    @Test
//    public void searchBranchesInServer_Success_Test() throws IOException {
//        BehaviorDelegate<RestRepository> delegate = mockRetrofit.create(RestRepository.class);
//        RestRepository mockRepository = new MockRestRepositorySuccess(delegate);
//
//        Call<ArrayList<BranchesTagsResponse>> quote = mockRepository.listBranches("thalesbm", "android-github-api");
//        Response<ArrayList<BranchesTagsResponse>> response = quote.execute();
//
//        assertTrue(response.isSuccessful());
//        assertEquals("android-github-api", response.body().get(0).getName());
//    }
//
//    @Captor
//    ArgumentCaptor<Callback> callbackArgumentCaptor;

    @Test
    public void should_test_on_response(){
//        Call<String> onResponseCall = mock(Call.class);
//
//        doAnswer((InvocationOnMock invocation) -> {
//            Response response = null;
//            invocation.getArgument(0);//, Callback.class).onResponse(onResponseCall, response);
//            return null;
//        }).when(onResponseCall).enqueue(any(Callback.class));
//
//       // sendData(....);
//
//        // verify function1
    }

    @Test
    public void testApiResponse() {
        RestRepository mockedApiInterface = Mockito.mock(RestRepository.class);
        Call<ArrayList<BranchesTagsResponse>> mockedCall = Mockito.mock(Call.class);

//        ArrayList<BranchesTagsResponse> list = new ArrayList<BranchesTagsResponse>();
        Mockito.when(mockedApiInterface.listBranches(anyString(), anyString())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<ArrayList<BranchesTagsResponse>> callback = invocation.getArgument(5);//, Callback.class);

                callback.onResponse(mockedCall, Response.success(new ArrayList<BranchesTagsResponse>()));
                // or callback.onResponse(mockedCall, Response.error(404. ...);
                // or callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(anyObject());

        // inject mocked ApiInterface to your presenter
        // and then mock view and verify calls (and eventually use ArgumentCaptor to access call parameters)
    }

    @Test
    public void test1() {
        String t = "aa";
        doAnswer(invocation -> {
            BranchPresenter callback = (BranchPresenter) invocation.getArguments()[0];
            callback.success(new ArrayList<>());
            return null;
        }).when(mModel).searchBranchesInServer(t, t);

        mRepository.searchBranchesInServer(t, t);
//        Thread.sleep(10000);
//
//        when(mModel.searchBranchesInServer(test, test)).thenAnswer(new Answer<Object>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                return null;
//            }
//        }).


//        verify(mPresenter).success(new ArrayList<>());
//
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