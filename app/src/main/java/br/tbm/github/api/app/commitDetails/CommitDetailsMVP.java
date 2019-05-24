package br.tbm.github.api.app.commitDetails;

import java.util.List;

import br.tbm.github.api.shared.presenter.BasePresenterCallbacks;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitFilesResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;
import retrofit2.Retrofit;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface CommitDetailsMVP {

    interface View extends BaseViewCallbacks<CommitsResponse> {
        void setCommitterName(String login);
        void setCommitDescription(String message);
        void setCommitterDate(String date);
        void listCommitsEmpty();
        void downloadProfileImage(String avatarUrl);
        void listCommits(List<CommitFilesResponse> commits);
    }

    interface Presenter extends BasePresenterCallbacks<CommitsResponse> {
        void searchCommitDetailsInServer(String username, String repositoryName, String sha);
    }

    interface Model {
        void searchCommitDetailsInServer(String username, String repositoryName, String sha);
        void setCallback(CommitDetailsMVP.Presenter presenter, Retrofit retrofit);
    }
}
