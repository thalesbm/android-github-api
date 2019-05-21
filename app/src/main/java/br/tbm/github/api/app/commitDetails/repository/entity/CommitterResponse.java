package br.tbm.github.api.app.commitDetails.repository.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.shared.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitterResponse implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("date")
    private String date;

    public CommitterResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(email);
        dest.writeValue(date);
    }

    public CommitterResponse(Parcel in) {
        this.name = ParcelableUtils.readValueToString(in);
        this.email = ParcelableUtils.readValueToString(in);
        this.date = ParcelableUtils.readValueToString(in);
    }

    public static final Parcelable.Creator<CommitterResponse> CREATOR = new Parcelable.Creator<CommitterResponse>() {
        public CommitterResponse createFromParcel(Parcel in) {
            return new CommitterResponse(in);
        }

        public CommitterResponse[] newArray(int size) {
            return new CommitterResponse[size];
        }
    };
}
