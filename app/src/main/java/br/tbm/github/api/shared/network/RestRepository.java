package br.tbm.github.api.shared.network;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.app.commitDetails.repository.entity.CommitsResponse;
import br.tbm.github.api.app.event.repository.entity.EventsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thalesbertolini on 22/08/2018
 **/
public interface RestRepository {

    @GET("repos/{username}/{project}/events")
    Call<ArrayList<EventsResponse>> listEvents(@Path("username") String username,
                                               @Path("project") String project);

    @GET("repos/{username}/{project}/tags")
    Call<ArrayList<BranchesTagsResponse>> listTags(@Path("username") String username,
                                                   @Path("project") String project);

    @GET("repos/{username}/{project}/branches")
    Call<ArrayList<BranchesTagsResponse>> listBranches(@Path("username") String username,
                                                       @Path("project") String project);

    @GET("repos/{username}/{project}/commits/{sha}")
    Call<CommitsResponse> listCommits(@Path("username") String username,
                                      @Path("project") String project,
                                      @Path("sha") String sha);
}