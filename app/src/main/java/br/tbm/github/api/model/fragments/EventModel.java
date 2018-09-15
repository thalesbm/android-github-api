package br.tbm.github.api.model.fragments;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.interfaces.EventMVP;
import br.tbm.github.api.network.entities.EventsResponse;
import br.tbm.github.api.network.rest.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class EventModel implements EventMVP.Model {

    private EventMVP.Presenter mPresenter;

    public EventModel(EventMVP.Presenter presenter) {
        this.mPresenter = presenter;
    }


    @Override
    public void searchInServer(String profileName, String repositoryName) {
        RestRepository service = GithubApplication.getRetrofitInstance().create(RestRepository.class);
        Call<ArrayList<EventsResponse>> responseCall = service.listEvents(profileName, repositoryName);
        responseCall.enqueue(new Callback<ArrayList<EventsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<EventsResponse>> call, @NonNull Response<ArrayList<EventsResponse>> response) {
                if (response.isSuccessful()) {
                    mPresenter.success(response.body());
                } else {
                    mPresenter.networkIssue(response.raw().code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<EventsResponse>> call, @NonNull Throwable t) {
                mPresenter.displayAlertDialog(t.getMessage());
            }
        });
    }
}
