package br.tbm.github.api.app.commitDetails.repository.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import br.tbm.github.api.shared.utils.ParcelableUtils;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitFilesResponse implements Parcelable {

    @SerializedName("sha")
    private String sha;

    @SerializedName("filename")
    private String fileName;

    @SerializedName("status")
    private String status;

    public CommitFilesResponse() {
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(sha);
        dest.writeValue(fileName);
        dest.writeValue(status);
    }

    public CommitFilesResponse(Parcel in) {
        this.sha = ParcelableUtils.readValueToString(in);
        this.fileName = ParcelableUtils.readValueToString(in);
        this.status = ParcelableUtils.readValueToString(in);
    }

    public static final Parcelable.Creator<CommitFilesResponse> CREATOR = new Parcelable.Creator<CommitFilesResponse>() {
        public CommitFilesResponse createFromParcel(Parcel in) {
            return new CommitFilesResponse(in);
        }

        public CommitFilesResponse[] newArray(int size) {
            return new CommitFilesResponse[size];
        }
    };
}