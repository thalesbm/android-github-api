package br.tbm.github.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventPayloadResponse implements Parcelable {

    @SerializedName("commits")
    private List<EventCommitsResponse> eventCommitsResponse;

    public EventPayloadResponse() {
    }

    public List<EventCommitsResponse> getEventCommitsResponse() {
        return eventCommitsResponse;
    }

    public void setEventCommitsResponse(List<EventCommitsResponse> eventCommitsResponse) {
        this.eventCommitsResponse = eventCommitsResponse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(eventCommitsResponse);
    }

    public EventPayloadResponse(Parcel in) {
        this.eventCommitsResponse = in.readArrayList(EventCommitsResponse.class.getClassLoader());
    }

    public static final Creator<EventPayloadResponse> CREATOR = new Creator<EventPayloadResponse>() {
        public EventPayloadResponse createFromParcel(Parcel in) {
            return new EventPayloadResponse(in);
        }

        public EventPayloadResponse[] newArray(int size) {
            return new EventPayloadResponse[size];
        }
    };
}
