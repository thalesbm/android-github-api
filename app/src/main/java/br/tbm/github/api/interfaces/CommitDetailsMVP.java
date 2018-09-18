package br.tbm.github.api.interfaces;

import java.util.List;

import br.tbm.github.api.interfaces.generic.BasePresenterCallbacks;
import br.tbm.github.api.network.entities.CommitFilesResponse;
import br.tbm.github.api.network.entities.CommitsResponse;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;

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
        void search(String username, String repositoryName, String sha);
    }

    interface Model {
        void searchInServer(String username, String repositoryName, String sha);

        void setCallback(CommitDetailsMVP.Presenter presenter);
    }
}
