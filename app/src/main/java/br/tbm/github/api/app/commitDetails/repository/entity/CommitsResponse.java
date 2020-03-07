package br.tbm.github.api.app.commitDetails.repository.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.tbm.github.api.app.profile.repository.entity.OwnerResponse;

/**
 * Created by thalesbertolini on 29/08/2018
 **/
public class CommitsResponse implements Parcelable {

    @SerializedName("committer")
    private OwnerResponse ownerResponse;

    @SerializedName("commit")
    private CommitDetailsResponse commitDetailsResponse;

    @SerializedName("files")
    private List<CommitFilesResponse> commitFilesResponse;

    public CommitsResponse() {
    }

    public OwnerResponse getOwnerResponse() {
        return ownerResponse;
    }

    public void setOwnerResponse(OwnerResponse ownerResponse) {
        this.ownerResponse = ownerResponse;
    }

    public CommitDetailsResponse getCommitDetailsResponse() {
        return commitDetailsResponse;
    }

    public void setCommitDetailsResponse(CommitDetailsResponse commitDetailsResponse) {
        this.commitDetailsResponse = commitDetailsResponse;
    }

    public List<CommitFilesResponse> getCommitFilesResponse() {
        return commitFilesResponse;
    }

    public void setCommitFilesResponse(List<CommitFilesResponse> commitFilesResponse) {
        this.commitFilesResponse = commitFilesResponse;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(ownerResponse, flags);
        dest.writeParcelable(commitDetailsResponse, flags);

        dest.writeList(commitFilesResponse);
    }

    public CommitsResponse(Parcel in) {
        this.ownerResponse = in.readParcelable(OwnerResponse.class.getClassLoader());
        this.commitDetailsResponse = in.readParcelable(CommitDetailsResponse.class.getClassLoader());

        this.commitFilesResponse = in.readArrayList(CommitFilesResponse.class.getClassLoader());
    }

    public static final Creator<CommitsResponse> CREATOR = new Creator<CommitsResponse>() {
        public CommitsResponse createFromParcel(Parcel in) {
            return new CommitsResponse(in);
        }

        public CommitsResponse[] newArray(int size) {
            return new CommitsResponse[size];
        }
    };

}
