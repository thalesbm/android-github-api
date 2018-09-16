package br.tbm.github.api.presenter.fragments;

import java.util.ArrayList;

import br.tbm.github.api.model.fragments.EventModel;
import br.tbm.github.api.network.entities.EventsResponse;
import br.tbm.github.api.interfaces.fragments.EventMVP;
import br.tbm.github.api.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class EventPresenter extends BasePresenter<EventsResponse> implements
        EventMVP.Presenter {

    private EventMVP.View mView;
    private EventMVP.Model mModel;

    public EventPresenter(EventMVP.View view) {
        this.mView = view;
        this.mModel = new EventModel(this);

        closeActivity = true;
    }

    /**
     * Metodo para pesquisar os eventos de um determinado perfil
     *
     * @param profileName String
     */
    @Override
    public void search(String profileName, String repositoryName) {
        mModel.searchInServer(profileName, repositoryName);
    }

    // ######################
    // CALLBACK DO MODEL
    // ######################

    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }

    @Override
    public void success(ArrayList<EventsResponse> response) {
        super.success(response);
        mView.success(response);
    }

    @Override
    public void networkIssue(int code) {
        super.networkIssue(code);
        mView.networkIssue(code, closeActivity);
    }
}
