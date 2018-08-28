package br.tbm.github.api.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventsResponse {

    @SerializedName("type")
    private String type;

    @SerializedName("actor")
    private EventActorResponse eventActorResponse;

    @SerializedName("payload")
    private EventPayloadResponse eventPayloadResponse;

    @SerializedName("created_at")
    private String created;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EventActorResponse getEventActorResponse() {
        return eventActorResponse;
    }

    public void setEventActorResponse(EventActorResponse eventActorResponse) {
        this.eventActorResponse = eventActorResponse;
    }

    public EventPayloadResponse getEventPayloadResponse() {
        return eventPayloadResponse;
    }

    public void setEventPayloadResponse(EventPayloadResponse eventPayloadResponse) {
        this.eventPayloadResponse = eventPayloadResponse;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}