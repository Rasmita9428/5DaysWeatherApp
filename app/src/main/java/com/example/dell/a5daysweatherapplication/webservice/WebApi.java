package com.example.dell.a5daysweatherapplication.webservice;

import com.example.dell.a5daysweatherapplication.model.Example;
import com.example.dell.a5daysweatherapplication.model.weekweather.WeekWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebApi {
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    @GET("weather")
    Call<Example> callweatherByLatLng(@Query("lat") String lat,
                                      @Query("lon") String lon,
                                      @Query("appid") String appid,
                                      @Query("units") String units);

    @GET("forecast")
    Call<WeekWeather> callWeekweatherByLatLng(@Query("lat") String lat,
                                              @Query("lon") String lon,
                                              @Query("appid") String appid);
}
