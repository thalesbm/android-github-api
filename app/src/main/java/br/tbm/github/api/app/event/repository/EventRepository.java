package br.tbm.github.api.app.event.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.app.event.presenter.IEventPresenter;
import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import br.tbm.github.api.shared.GithubApplication;
import br.tbm.github.api.shared.network.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public class EventRepository implements IEventRepository {

    private IEventPresenter mPresenter;

    @Override
    public void searchEventsInServer(String profileName, String repositoryName) {
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

    /**
     * Metodo responsavel por adicionar a instancia do presenter no repository
     *
     * @param presenter EventMVP.Presenter
     */
    @Override
    public void setCallback(IEventPresenter presenter) {
        this.mPresenter = presenter;
    }
}
