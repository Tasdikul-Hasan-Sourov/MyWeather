package com.example.myweatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CityApi {
    @GET("weather?appid=d16d896d2210757e6998d3f776dc89fb&units=metric")
    Call<Example> getCity(@Query("q") String name);
    @GET("forecast?appid=d16d896d2210757e6998d3f776dc89fb&units=metric")
    Call<Example2> getForcast(@Query("q") String name);
}
