package br.tbm.github.api.database.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
@DatabaseTable(tableName = "tb_historic")
public class Profile implements Parcelable {

    @DatabaseField(generatedId = true)
    @SerializedName("internal_id")
    private Long id;

    @DatabaseField(canBeNull = false)
    @SerializedName("id")
    private Long githubID;

    @DatabaseField(canBeNull = false)
    @SerializedName("name")
    private String name;

    @DatabaseField(canBeNull = false)
    @SerializedName("login")
    private String login;

    @DatabaseField
    @SerializedName("avatar_url")
    private String avatarUrl;

    @DatabaseField(canBeNull = false)
    private Date created;

    private boolean hasSelected;

    public Profile() {
    }

    public Profile(String name) {
        this.name = name;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getGithubID() {
        return githubID;
    }

    public void setGithubID(Long githubID) {
        this.githubID = githubID;
    }

    public boolean hasSelected() {
        return hasSelected;
    }

    public void setHasSelected(boolean hasSelected) {
        this.hasSelected = hasSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(githubID);
        dest.writeValue(name);
        dest.writeValue(login);
        dest.writeValue(avatarUrl);
        dest.writeValue(created);
    }

    public Profile(Parcel in) {
        this.id = ParcelableUtils.readValueToLong(in);
        this.githubID = ParcelableUtils.readValueToLong(in);
        this.name = ParcelableUtils.readValueToString(in);
        this.login = ParcelableUtils.readValueToString(in);
        this.avatarUrl = ParcelableUtils.readValueToString(in);
        this.created = ParcelableUtils.readValueToDate(in);
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
