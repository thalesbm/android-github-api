package br.tbm.github.api.controllers;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import br.tbm.github.api.GithubApplication;
import br.tbm.github.api.entities.EventsResponse;
import br.tbm.github.api.interfaces.ControllerCallbacks;
import br.tbm.github.api.rest.RestRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class EventController {

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    private final boolean closeActivity = true;

    private ControllerCallbacks<EventsResponse> mCallback;

    public EventController(ControllerCallbacks<EventsResponse> callback) {
        this.mCallback = callback;
    }

    /**
     * Metodo para pesquisar os eventos de um determinado perfil
     *
     * @param profileName String
     */
    public void search(String profileName, String repositoryName) {
        RestRepository service = GithubApplication.getRetrofitInstance().create(RestRepository.class);
        Call<ArrayList<EventsResponse>> responseCall = service.listEvents(profileName, repositoryName);
        responseCall.enqueue(new Callback<ArrayList<EventsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<EventsResponse>> call, @NonNull Response<ArrayList<EventsResponse>> response) {
                if (response.isSuccessful()) {
                    mCallback.success(response.body());
                } else {
                    mCallback.networkIssue(response.raw().code(), closeActivity);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<EventsResponse>> call, @NonNull Throwable t) {
                mCallback.displayAlertDialog(t.getMessage(), closeActivity);
            }
        });
    }
}
