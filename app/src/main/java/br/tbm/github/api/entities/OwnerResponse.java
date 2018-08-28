package br.tbm.github.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class OwnerResponse implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("id")
    private Long id;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("url")
    private String url;

    @SerializedName("type")
    private String type;

    public OwnerResponse() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(login);
        dest.writeValue(id);
        dest.writeValue(avatarUrl);
        dest.writeValue(url);
        dest.writeValue(type);
    }

    public OwnerResponse(Parcel in) {
        this.login = ParcelableUtils.readValueToString(in);
        this.id = ParcelableUtils.readValueToLong(in);
        this.avatarUrl = ParcelableUtils.readValueToString(in);
        this.url = ParcelableUtils.readValueToString(in);
        this.type = ParcelableUtils.readValueToString(in);
    }

    public static final Creator<OwnerResponse> CREATOR = new Creator<OwnerResponse>() {
        public OwnerResponse createFromParcel(Parcel in) {
            return new OwnerResponse(in);
        }

        public OwnerResponse[] newArray(int size) {
            return new OwnerResponse[size];
        }
    };
}