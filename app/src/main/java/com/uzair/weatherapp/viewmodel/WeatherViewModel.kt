package com.uzair.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uzair.weatherapp.data.Resource
import com.uzair.weatherapp.data.Forecast
import com.uzair.weatherapp.data.Temperature
import com.uzair.weatherapp.service.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repo: WeatherRepository) :
    ViewModel() {

    private var temperature: String? = null
    fun getTemp(): MutableLiveData<Resource<Pair<Temperature, Forecast>>> {
        val result = MutableLiveData<Resource<Pair<Temperature, Forecast>>>()
        result.value = Resource.loading()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(repo.getTemp())
        }
        return result
    }
}