package br.tbm.github.api.app.event.presenter;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.app.event.EventMVP;
import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import br.tbm.github.api.shared.presenter.BasePresenter;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
public class EventPresenter extends BasePresenter<EventsResponse> implements
        EventMVP.Presenter {

    private EventMVP.View mView;
    private EventMVP.Model mModel;

    public EventPresenter(EventMVP.View view, EventMVP.Model model) {
        super();
        this.mView = view;
        this.mModel = model;
        this.mModel.setCallback(this);
    }

    @Override
    public void needsToCloseCurrentActivity() {
        closeActivity = true;
    }

    @Override
    public void searchEventsInServer(String profileName, String repositoryName) {
        mView.updateProgressDialog(R.string.loading);
        mModel.searchEventsInServer(profileName, repositoryName);
    }

    // ######################
    // CALLBACK DO REPOSITORY
    // ######################

    @Override
    public void displayAlertDialog(int id) {
        super.displayAlertDialog(id);
        mView.displayAlertDialog(id, closeActivity);
    }

    @Override
    public void success(ArrayList<EventsResponse> response) {
        super.success(response);

        if (response.isEmpty()) {
            mView.listEventsEmpty();
        } else {
            mView.listEvents(response);
        }
    }

    @Override
    public void networkIssue(int code) {
        super.networkIssue(code);
        mView.networkIssue(code, closeActivity);
    }
}
