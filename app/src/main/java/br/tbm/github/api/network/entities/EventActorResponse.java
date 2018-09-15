package br.tbm.github.api.network.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventActorResponse implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("display_login")
    private String displayLogin;

    @SerializedName("url")
    private String url;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public EventActorResponse() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public void setDisplayLogin(String displayLogin) {
        this.displayLogin = displayLogin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(login);
        dest.writeValue(displayLogin);
        dest.writeValue(url);
        dest.writeValue(avatarUrl);
    }

    public EventActorResponse(Parcel in) {
        this.login = ParcelableUtils.readValueToString(in);
        this.displayLogin = ParcelableUtils.readValueToString(in);
        this.url = ParcelableUtils.readValueToString(in);
        this.avatarUrl = ParcelableUtils.readValueToString(in);
    }

    public static final Creator<EventActorResponse> CREATOR = new Creator<EventActorResponse>() {
        public EventActorResponse createFromParcel(Parcel in) {
            return new EventActorResponse(in);
        }

        public EventActorResponse[] newArray(int size) {
            return new EventActorResponse[size];
        }
    };
}
