package com.uzair.weatherapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uzair.weatherapp.R
import com.uzair.weatherapp.data.Forecast
import com.uzair.weatherapp.databinding.ForecastRowItemBinding
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private var data: List<Forecast.Predict>? = null
    fun populateData(forecast: List<Forecast.Predict>) {
        data = forecast
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ForecastRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataset: Forecast.Predict) {
            val temp = dataset.main?.temp.toString()
            binding.forecastValue.text =
                binding.root.resources.getString(R.string.celsius, temp.dropLast(temp.length - 5))
            binding.forecastDay.text = parseDate(dataset)
        }


        private fun parseDate(dataset: Forecast.Predict): String? {
            return try {

                val parse =
                    SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss",
                        Locale.getDefault()
                    ).parse(dataset.dt_txt.toString())
                val sdf = SimpleDateFormat("EEEE",Locale.getDefault())

                sdf.format(parse)
            } catch (e: Exception) {
                null
            }
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
