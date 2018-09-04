package br.tbm.github.api.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class ResourcesResponse implements Parcelable {

    @SerializedName("resources")
    private RateLimitResponse rateLimitResponse;

    public ResourcesResponse() {
    }

    public RateLimitResponse getRateLimitResponse() {
        return rateLimitResponse;
    }

    public void setRateLimitResponse(RateLimitResponse rateLimitResponse) {
        this.rateLimitResponse = rateLimitResponse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(rateLimitResponse, flags);
    }

    public ResourcesResponse(Parcel in) {
        this.rateLimitResponse = in.readParcelable(RateLimitResponse.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResourcesResponse> CREATOR = new Parcelable.Creator<ResourcesResponse>() {
        public ResourcesResponse createFromParcel(Parcel in) {
            return new ResourcesResponse(in);
        }

        public ResourcesResponse[] newArray(int size) {
            return new ResourcesResponse[size];
        }
    };
}
