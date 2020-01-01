package br.tbm.github.api.shared.network;

import java.util.ArrayList;

import br.tbm.github.api.app.profile.repository.entity.RepositoriesResponse;
import br.tbm.github.api.app.profile.repository.model.Profile;
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