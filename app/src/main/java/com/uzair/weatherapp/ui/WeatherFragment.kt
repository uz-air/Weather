package com.uzair.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.uzair.weatherapp.R
import com.uzair.weatherapp.data.Resource
import com.uzair.weatherapp.databinding.FragmentWeatherBinding
import com.uzair.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.view.animation.TranslateAnimation
import androidx.core.view.isVisible
import com.uzair.weatherapp.di.AppModule
import javax.inject.Inject


@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherViewModel by viewModels()

    @Inject
    lateinit var progressDialogFactory: AppModule.ProgressDialogFactory

    private val progressDialog: ProgressDialog by lazy { progressDialogFactory.create(this.context!!) }
    private val forecastAdapter by lazy { ForecastAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = forecastAdapter
        viewModel.getValues().observe(this) { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    progressDialog.hideLoading()
                    resource?.data?.let {
                        val temp = it.first.main?.temp
                        val forecast = it.second.list
                        val placeName = it.first.name
                        if (temp != null && forecast != null && placeName != null) {
                            binding.tempToday.text =
                                resources.getString(R.string.degrees, temp.toString())
                            binding.placeName.text = placeName
                            forecastAdapter.populateData(forecast)
                            slideIn()
                        } else
                            showErrorScreen()
                    } ?: showErrorScreen()
                }
                Resource.Status.ERROR -> {
                    progressDialog.hideLoading()
                    showErrorScreen()
                }
                Resource.Status.LOADING -> {
                    progressDialog.showLoading()
                    showLoadingScreen()
                }
            }
        }
    }

    private fun slideIn() {
        binding.recyclerView.isVisible = true
        val animate = TranslateAnimation(
            0F,
            0F,
            binding.recyclerView.height.toFloat(),
            0F
        ).apply {
            duration = 1000
            fillAfter = true
        }
        binding.recyclerView.startAnimation(animate)
    }

    private fun showLoadingScreen() {

    }

    private fun showErrorScreen() {
        findNavController().navigate(R.id.weatherFragment_to_errorFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}