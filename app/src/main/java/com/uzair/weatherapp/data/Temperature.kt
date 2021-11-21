package com.uzair.weatherapp.data

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("base") val base: String,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("cod") val cod: Int,
    @SerializedName("coord") val coord: Coord,
    @SerializedName("dt") val dt: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: Main,
    @SerializedName("name") val name: String,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("wind") val wind: Wind
) {
    data class Clouds(
        @SerializedName("all") val all: Int
    )

    data class Coord(
        @SerializedName("lat") val lat: Double,
        @SerializedName("lon") val lon: Double
    )

    data class Main(
        @SerializedName("feels_like") val feels_like: Double,
        @SerializedName("grnd_level") val grnd_level: Int,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("sea_level") val sea_level: Int,
        @SerializedName("temp") val temp: Double,
        @SerializedName("temp_max") val temp_max: Double,
        @SerializedName("temp_min") val temp_min: Double
    )

    data class Sys(
        @SerializedName("country") val country: String,
        @SerializedName("id") val id: Int,
        @SerializedName("sunrise") val sunrise: Int,
        @SerializedName("sunset") val sunset: Int,
        @SerializedName("type") val type: Int
    )

    data class Weather(
        @SerializedName("description") val description: String,
        @SerializedName("icon") val icon: String,
        @SerializedName("id") val id: Int,
        @SerializedName("main") val main: String
    )

    data class Wind(
        @SerializedName("deg") val deg: Int,
        @SerializedName("gust") val gust: Double,
        @SerializedName("speed") val speed: Double
    )
}