package br.tbm.github.api.rest;

import java.util.ArrayList;

import br.tbm.github.api.entities.BranchesTagsResponse;
import br.tbm.github.api.entities.EventsResponse;
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
    Call<ArrayList<BranchesTagsResponse>> getCommit(@Path("username") String username,
                                                    @Path("project") String project,
                                                    @Path("sha") String sha);
}