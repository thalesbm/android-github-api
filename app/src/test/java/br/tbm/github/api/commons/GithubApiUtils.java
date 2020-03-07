package br.tbm.github.api.commons;

import java.util.ArrayList;
import java.util.List;

import br.tbm.github.api.app.commitDetails.repository.entity.CommitDetailsResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitFilesResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitterResponse;
import br.tbm.github.api.app.profile.repository.entity.OwnerResponse;

public class GithubApiUtils {

    public static CommitsResponse getCommitsResponse() {
        CommitsResponse response = new CommitsResponse();

        List<CommitFilesResponse> files = new ArrayList<>();

        OwnerResponse owner = new OwnerResponse();
        owner.setLogin("Thales Marega");

        CommitterResponse committer = new CommitterResponse();
        committer.setDate("2018-09-20T09:45:00"); // yyyy-MM-dd'T'HH:mm:ss

        CommitDetailsResponse details = new CommitDetailsResponse();
        details.setMessage("ADDED TESTS");
        details.setCommitterResponse(committer);

        response.setCommitDetailsResponse(details);
        response.setOwnerResponse(owner);
        response.setCommitFilesResponse(files);
        return response;
    }
}
