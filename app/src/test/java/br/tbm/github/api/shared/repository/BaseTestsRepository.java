package br.tbm.github.api.shared.repository;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class BaseTestsRepository {

    protected MockRetrofit mockRetrofit;
    protected Retrofit retrofit;

    protected void setUp() {
        retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create()).build();

        NetworkBehavior behavior = NetworkBehavior.create();

        mockRetrofit = new MockRetrofit.Builder(retrofit).networkBehavior(behavior).build();
    }

}
