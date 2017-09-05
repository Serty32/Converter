package com.example.sinelnikovserhii.converter;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sinelnikovserhii on 28.08.17.
 */

public interface CurrencyApi {
    @GET("latest")
    Call<FixerResponse> getData(@Query("base") String currency, @Query("symbols") String currencyTo);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://api.fixer.io/")
            .addConverterFactory(GsonConverterFactory.create()).build();
}
