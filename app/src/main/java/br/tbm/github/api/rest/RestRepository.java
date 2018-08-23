package br.tbm.github.api.rest;

import java.util.ArrayList;

import br.tbm.github.api.entities.RepositoriesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thalesbertolini on 22/08/2018
 **/
public interface RestRepository {

    @GET("repos/{username}/{project}/events")
    Call<ArrayList<RepositoriesResponse>> listCommits(@Path("username") String username,
                                                      @Path("project") String project);

    @GET("repos/{username}/{project}/tags")
    Call<ArrayList<RepositoriesResponse>> listTags(@Path("username") String username,
                                                   @Path("project") String project);

    @GET("repos/{username}/{project}/branches")
    Call<ArrayList<RepositoriesResponse>> listBranches(@Path("username") String username,
                                                       @Path("project") String project);
}