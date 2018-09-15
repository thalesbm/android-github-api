package br.tbm.github.api.network.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitDetailsResponse implements Parcelable {

    @SerializedName("message")
    private String message;

    @SerializedName("committer")
    private CommitterResponse committerResponse;

    public CommitDetailsResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommitterResponse getCommitterResponse() {
        return committerResponse;
    }

    public void setCommitterResponse(CommitterResponse committerResponse) {
        this.committerResponse = committerResponse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);

        dest.writeParcelable(committerResponse, flags);
    }

    public CommitDetailsResponse(Parcel in) {
        this.message = ParcelableUtils.readValueToString(in);

        this.committerResponse = in.readParcelable(CommitterResponse.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommitDetailsResponse> CREATOR = new Parcelable.Creator<CommitDetailsResponse>() {
        public CommitDetailsResponse createFromParcel(Parcel in) {
            return new CommitDetailsResponse(in);
        }

        public CommitDetailsResponse[] newArray(int size) {
            return new CommitDetailsResponse[size];
        }
    };
}
