package br.tbm.github.api.rest;

import java.util.ArrayList;

import br.tbm.github.api.entities.ResourcesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by thalesbertolini on 04/09/2018
 **/
public interface RestGithub {

    @GET("rate_limit")
    Call<ResourcesResponse> getRateLimit();
}