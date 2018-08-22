package br.tbm.github.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class ProfileResponse implements Parcelable {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public ProfileResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(login);
        dest.writeValue(avatarUrl);
    }

    public ProfileResponse(Parcel in) {
        this.id = ParcelableUtils.readValueToLong(in);
        this.name = ParcelableUtils.readValueToString(in);
        this.login = ParcelableUtils.readValueToString(in);
        this.avatarUrl = ParcelableUtils.readValueToString(in);
    }

    public static final Creator<ProfileResponse> CREATOR = new Creator<ProfileResponse>() {
        public ProfileResponse createFromParcel(Parcel in) {
            return new ProfileResponse(in);
        }

        public ProfileResponse[] newArray(int size) {
            return new ProfileResponse[size];
        }
    };
}
