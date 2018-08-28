package br.tbm.github.api.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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
