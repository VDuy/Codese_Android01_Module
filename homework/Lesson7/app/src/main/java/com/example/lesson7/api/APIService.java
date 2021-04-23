package com.example.lesson7.api;

import com.example.lesson7.models.GetWeathersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("weather")
    Call<GetWeathersResponse> getWeather(
            @Query("q") String cityName,
            @Query("units") String temperature,
            @Query("appid") String api_key

    );
}
