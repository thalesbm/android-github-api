package br.tbm.github.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventCommitsResponse implements Parcelable {

    @SerializedName("message")
    private String message;

    @SerializedName("url")
    private String url;

    @SerializedName("sha")
    private String sha;

    public EventCommitsResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(sha);
        dest.writeValue(url);
    }

    public EventCommitsResponse(Parcel in) {
        this.message = ParcelableUtils.readValueToString(in);
        this.sha = ParcelableUtils.readValueToString(in);
        this.url = ParcelableUtils.readValueToString(in);
    }

    public static final Creator<EventCommitsResponse> CREATOR = new Creator<EventCommitsResponse>() {
        public EventCommitsResponse createFromParcel(Parcel in) {
            return new EventCommitsResponse(in);
        }

        public EventCommitsResponse[] newArray(int size) {
            return new EventCommitsResponse[size];
        }
    };
}
