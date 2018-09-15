package br.tbm.github.api.network.rest;

import br.tbm.github.api.network.entities.ResourcesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public interface RestGithub {

    @GET("rate_limit")
    Call<ResourcesResponse> getRateLimit();
}