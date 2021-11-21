package com.uzair.weatherapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uzair.weatherapp.data.Forecast
import com.uzair.weatherapp.databinding.ForecastRowItemBinding

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private var data: List<Forecast.Predict>? = null
    fun populateData(forecast: List<Forecast.Predict>) {
        data = forecast
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ForecastRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataset: Forecast.Predict) {
            binding.forecastValue.text = dataset.main?.temp.toString()
            binding.forecastDay.text = dataset.dt_txt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ForecastRowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }
}
