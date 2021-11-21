package com.uzair.weatherapp.service

import com.uzair.weatherapp.BuildConfig
import com.uzair.weatherapp.data.Forecast
import com.uzair.weatherapp.data.Temperature
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    suspend fun getTemp(
        @Query("q") location: String = "Bengaluru",
        @Query("APPID") appId: String = BuildConfig.APP_ID,
        @Query("units") units: String = "metric"
    ): Temperature

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") location: String = "Bengaluru",
        @Query("APPID") appId: String = BuildConfig.APP_ID,
        @Query("units") units: String = "metric"
    ): Forecast

}