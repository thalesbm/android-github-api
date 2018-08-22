package br.tbm.github.api.rest;

import java.util.ArrayList;

import br.tbm.github.api.entities.ProfileResponse;
import br.tbm.github.api.entities.RepositoriesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public interface RestService {

    // see all commits
    // https://api.github.com/repos/thalesbm/android-entrevistas-tests/events

    // see all branches
    // https://api.github.com/repos/thalesbm/android-entrevistas-tests/branches

    // see all tags
    // https://api.github.com/repos/thalesbm/android-entrevistas-tests/tags

    @GET("{username}/repos")
    Call<ArrayList<RepositoriesResponse>> listRepositories(@Path("username") String username);

    @GET("{username}")
    Call<ProfileResponse> getProfile(@Path("username") String username);
}