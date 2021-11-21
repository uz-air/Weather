package com.uzair.weatherapp.service

import com.uzair.weatherapp.data.Forecast
import com.uzair.weatherapp.data.Resource
import com.uzair.weatherapp.data.Temperature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val retrofit: WeatherService) {

    suspend fun getValues(): Resource<Pair<Temperature, Forecast>> { //5
        return withContext(Dispatchers.IO) {
            try {
                val second = retrofit.getForecast()
                val first = retrofit.getTemp()
                Resource.success(Pair(first, second))
            } catch (e: Throwable) {
                Resource.error(e)
            }
        }
    }
}