package br.tbm.github.api.network.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public class RateLimitItemsResponse implements Parcelable {

    @SerializedName("limit")
    private int limit;

    @SerializedName("remaining")
    private int remaining;

    public RateLimitItemsResponse() {
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(limit);
        dest.writeValue(remaining);
    }

    public RateLimitItemsResponse(Parcel in) {
        this.limit = ParcelableUtils.readValueToInteger(in);
        this.remaining = ParcelableUtils.readValueToInteger(in);
    }

    public static final Parcelable.Creator<RateLimitItemsResponse> CREATOR = new Parcelable.Creator<RateLimitItemsResponse>() {
        public RateLimitItemsResponse createFromParcel(Parcel in) {
            return new RateLimitItemsResponse(in);
        }

        public RateLimitItemsResponse[] newArray(int size) {
            return new RateLimitItemsResponse[size];
        }
    };
}
