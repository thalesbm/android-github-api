package br.tbm.github.api.network.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 27/08/2018
 **/
public class BranchesTagsResponse implements Parcelable {

    @SerializedName("name")
    private String name;

    public BranchesTagsResponse() {
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
        dest.writeValue(name);
    }

    public BranchesTagsResponse(Parcel in) {
        this.name = ParcelableUtils.readValueToString(in);
    }

    public static final Creator<BranchesTagsResponse> CREATOR = new Creator<BranchesTagsResponse>() {
        public BranchesTagsResponse createFromParcel(Parcel in) {
            return new BranchesTagsResponse(in);
        }

        public BranchesTagsResponse[] newArray(int size) {
            return new BranchesTagsResponse[size];
        }
    };
}