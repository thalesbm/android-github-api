package br.tbm.github.api.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventPayloadResponse {

    @SerializedName("commits")
    private List<EventCommitsResponse> eventCommitsResponse;

    public List<EventCommitsResponse> getEventCommitsResponse() {
        return eventCommitsResponse;
    }

    public void setEventCommitsResponse(List<EventCommitsResponse> eventCommitsResponse) {
        this.eventCommitsResponse = eventCommitsResponse;
    }
}
