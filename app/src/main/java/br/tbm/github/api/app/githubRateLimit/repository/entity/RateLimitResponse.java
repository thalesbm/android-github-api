package br.tbm.github.api.app.githubRateLimit.repository.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class RateLimitResponse implements Parcelable {

    @SerializedName("core")
    private RateLimitItemsResponse core;

    @SerializedName("searchEventsInServer")
    private RateLimitItemsResponse search;

    @SerializedName("graphql")
    private RateLimitItemsResponse graphql;

    public RateLimitResponse() {
    }

    public RateLimitItemsResponse getCore() {
        return core;
    }

    public void setCore(RateLimitItemsResponse core) {
        this.core = core;
    }

    public RateLimitItemsResponse getSearch() {
        return search;
    }

    public void setSearch(RateLimitItemsResponse search) {
        this.search = search;
    }

    public RateLimitItemsResponse getGraphql() {
        return graphql;
    }

    public void setGraphql(RateLimitItemsResponse graphql) {
        this.graphql = graphql;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(core, flags);
        dest.writeParcelable(search, flags);
        dest.writeParcelable(graphql, flags);
    }

    public RateLimitResponse(Parcel in) {
        this.core = in.readParcelable(RateLimitItemsResponse.class.getClassLoader());
        this.search = in.readParcelable(RateLimitItemsResponse.class.getClassLoader());
        this.graphql = in.readParcelable(RateLimitItemsResponse.class.getClassLoader());
    }

    public static final Parcelable.Creator<RateLimitResponse> CREATOR = new Parcelable.Creator<RateLimitResponse>() {
        public RateLimitResponse createFromParcel(Parcel in) {
            return new RateLimitResponse(in);
        }

        public RateLimitResponse[] newArray(int size) {
            return new RateLimitResponse[size];
        }
    };
}
