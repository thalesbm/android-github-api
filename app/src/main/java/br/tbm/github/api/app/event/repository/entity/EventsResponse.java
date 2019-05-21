package br.tbm.github.api.app.event.repository.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.shared.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventsResponse implements Parcelable {

    @SerializedName("type")
    private String type;

    @SerializedName("actor")
    private EventActorResponse eventActorResponse;

    @SerializedName("payload")
    private EventPayloadResponse eventPayloadResponse;

    @SerializedName("created_at")
    private String created;

    public EventsResponse() {
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(created);

        dest.writeParcelable(eventPayloadResponse, flags);
        dest.writeParcelable(eventActorResponse, flags);
    }

    public EventsResponse(Parcel in) {
        this.type = ParcelableUtils.readValueToString(in);
        this.created = ParcelableUtils.readValueToString(in);

        this.eventPayloadResponse = in.readParcelable(EventPayloadResponse.class.getClassLoader());
        this.eventActorResponse = in.readParcelable(EventActorResponse.class.getClassLoader());
    }

    public static final Creator<EventsResponse> CREATOR = new Creator<EventsResponse>() {
        public EventsResponse createFromParcel(Parcel in) {
            return new EventsResponse(in);
        }

        public EventsResponse[] newArray(int size) {
            return new EventsResponse[size];
        }
    };
}