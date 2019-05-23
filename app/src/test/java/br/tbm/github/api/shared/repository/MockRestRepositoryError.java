//package br.tbm.github.api.shared.repository;
//
//import android.util.Log;
//
//import java.util.ArrayList;
//
//import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
//import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
//import br.tbm.github.api.app.event.repository.entity.EventsResponse;
//import br.tbm.github.api.shared.network.RestRepository;
//import okhttp3.MediaType;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.mock.BehaviorDelegate;
//import retrofit2.mock.Calls;
//
//public class MockRestRepositoryError implements RestRepository {
//
//    private final BehaviorDelegate<RestRepository> delegate;
//
//    public MockRestRepositoryError(BehaviorDelegate<RestRepository> service) {
//        this.delegate = service;
//    }
//
//    @Override
//    public Call<ArrayList<EventsResponse>> listEvents(String username, String project) {
//        return null;
//    }
//
//    @Override
//    public Call<ArrayList<BranchesTagsResponse>> listTags(String username, String project) {
//        return null;
//    }
//
//    @Override
//    public Call<ArrayList<BranchesTagsResponse>> listBranches(String username, String project) {
////        ArrayList<BranchesTagsResponse> list = new ArrayList<>();
////        BranchesTagsResponse branch = new BranchesTagsResponse();
////        branch.setName("android-github-api");
////        list.add(branch);
////        return delegate.returningResponse(list).listBranches("thalesbm92", "android-github-api");
//        Response response = Response.error(404, ResponseBody.create(MediaType.parse("application/json"), ""));
//        return delegate.returningResponse(response).listBranches("thalesbm92", "android-github-api");
//    }
//
//    @Override
//    public Call<CommitsResponse> listCommits(String username, String project, String sha) {
//        return null;
//    }
//}
