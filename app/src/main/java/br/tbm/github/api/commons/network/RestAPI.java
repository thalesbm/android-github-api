package br.tbm.github.api.commons.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public class RestAPI {
    private static final String URL = "https://api.github.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.networkInterceptors().add(httpLoggingInterceptor);
        client.addInterceptor((chain) -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Accept", "application/vnd.github.v3+json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);
        });

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client.build())
                    .build();
        }

        return retrofit;
    }
}
