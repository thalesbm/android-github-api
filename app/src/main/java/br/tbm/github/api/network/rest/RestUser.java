package br.tbm.github.api.network.rest;

import java.util.ArrayList;

import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.network.entities.RepositoriesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public interface RestUser {

    @GET("users/{username}/repos")
    Call<ArrayList<RepositoriesResponse>> listRepositories(@Path("username") String username);

    @GET("users/{username}")
    Call<Profile> getProfile(@Path("username") String username);
}