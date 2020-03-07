package br.tbm.github.api.app.event.repository.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.commons.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class EventAuthorResponse implements Parcelable {

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    public EventAuthorResponse() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(email);
        dest.writeValue(name);
    }

    public EventAuthorResponse(Parcel in) {
        this.email = ParcelableUtils.readValueToString(in);
        this.name = ParcelableUtils.readValueToString(in);
    }

    public static final Creator<EventAuthorResponse> CREATOR = new Creator<EventAuthorResponse>() {
        public EventAuthorResponse createFromParcel(Parcel in) {
            return new EventAuthorResponse(in);
        }

        public EventAuthorResponse[] newArray(int size) {
            return new EventAuthorResponse[size];
        }
    };
}
