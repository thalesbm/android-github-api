package br.tbm.github.api.app.profile.repository.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.commons.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class RepositoriesResponse implements Parcelable {

    @SerializedName("id")
    private Long id;

    @SerializedName("node_id")
    private String nodeId;

    @SerializedName("name")
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("private")
    private String type;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("url")
    private String url;

    @SerializedName("owner")
    private OwnerResponse ownerResponse;

    @SerializedName("language")
    private String language;

    public RepositoriesResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public OwnerResponse getOwnerResponse() {
        return ownerResponse;
    }

    public void setOwnerResponse(OwnerResponse ownerResponse) {
        this.ownerResponse = ownerResponse;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nodeId);
        dest.writeValue(name);
        dest.writeValue(fullName);
        dest.writeValue(type);
        dest.writeValue(htmlUrl);
        dest.writeValue(description);
        dest.writeValue(url);
        dest.writeValue(language);

        dest.writeParcelable(ownerResponse, flags);
    }

    public RepositoriesResponse(Parcel in) {
        this.id = ParcelableUtils.readValueToLong(in);
        this.nodeId = ParcelableUtils.readValueToString(in);
        this.name = ParcelableUtils.readValueToString(in);
        this.fullName = ParcelableUtils.readValueToString(in);
        this.type = ParcelableUtils.readValueToString(in);
        this.htmlUrl = ParcelableUtils.readValueToString(in);
        this.description = ParcelableUtils.readValueToString(in);
        this.url = ParcelableUtils.readValueToString(in);
        this.language = ParcelableUtils.readValueToString(in);

        this.ownerResponse = in.readParcelable(OwnerResponse.class.getClassLoader());
    }

    public static final Parcelable.Creator<RepositoriesResponse> CREATOR = new Parcelable.Creator<RepositoriesResponse>() {
        public RepositoriesResponse createFromParcel(Parcel in) {
            return new RepositoriesResponse(in);
        }

        public RepositoriesResponse[] newArray(int size) {
            return new RepositoriesResponse[size];
        }
    };
}
