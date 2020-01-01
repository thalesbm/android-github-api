package br.tbm.github.api.app.commitDetails.view;

import java.util.List;

import br.tbm.github.api.app.commitDetails.repository.entity.CommitFilesResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ICommitDetailsView extends BaseViewCallbacks<CommitsResponse> {
    void setCommitterName(String login);
    void setCommitDescription(String message);
    void setCommitterDate(String date);
    void listCommitsEmpty();
    void downloadProfileImage(String avatarUrl);
    void listCommits(List<CommitFilesResponse> commits);
}
