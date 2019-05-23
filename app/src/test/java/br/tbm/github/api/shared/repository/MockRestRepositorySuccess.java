package br.tbm.github.api.shared.repository;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import br.tbm.github.api.shared.network.RestRepository;
import retrofit2.Call;
import retrofit2.mock.BehaviorDelegate;

public class MockRestRepositorySuccess implements RestRepository {

    private final BehaviorDelegate<RestRepository> delegate;

    public MockRestRepositorySuccess(BehaviorDelegate<RestRepository> service) {
        this.delegate = service;
    }

    @Override
    public Call<ArrayList<EventsResponse>> listEvents(String username, String project) {
        return null;
    }

    @Override
    public Call<ArrayList<BranchesTagsResponse>> listTags(String username, String project) {
        return null;
    }

    @Override
    public Call<ArrayList<BranchesTagsResponse>> listBranches(String username, String project) {
        ArrayList<BranchesTagsResponse> list = new ArrayList<>();
        BranchesTagsResponse branch = new BranchesTagsResponse();
        branch.setName("android-github-api");
        list.add(branch);
        return delegate.returningResponse(list).listBranches("thalesbm92", "android-github-api");
    }

    @Override
    public Call<CommitsResponse> listCommits(String username, String project, String sha) {
        return null;
    }
}
