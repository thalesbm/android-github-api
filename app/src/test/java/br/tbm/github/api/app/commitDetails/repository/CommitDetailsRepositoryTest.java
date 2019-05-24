package br.tbm.github.api.app.commitDetails.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.BranchRepository;
import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.commitDetails.CommitDetailsMVP;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.shared.network.RestRepository;
import br.tbm.github.api.shared.repository.BaseTestsRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;

@RunWith(MockitoJUnitRunner.class)
public class CommitDetailsRepositoryTest extends BaseTestsRepository {

    @Mock
    CommitDetailsMVP.Model mModel;

    @Mock
    CommitDetailsMVP.Presenter mPresenter;

    private CommitDetailsRepository mRepository;

    @Captor
    private ArgumentCaptor<Callback<CommitsResponse>> callbackArgumentCaptor;

    @Before
    public void setUp() {
        super.setUp();

        mRepository = new CommitDetailsRepository();
        mRepository.setCallback(mPresenter, retrofit);
    }

    @Test
    public void searchCommitDetailsInServer() {

        callbackArgumentCaptor.capture();

        RestRepository mockedApiInterface = Mockito.mock(RestRepository.class);
        Call<CommitsResponse> mockedCall = Mockito.mock(Call.class);

        Mockito.when(mockedApiInterface.listCommits(anyString(), anyString(), anyString())).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Callback<CommitsResponse> callback = invocation.getArgument(5);//, Callback.class);

                callback.onResponse(mockedCall, Response.success(new CommitsResponse()));
                // or callback.onResponse(mockedCall, Response.error(404. ...);
                // or callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
    }
}