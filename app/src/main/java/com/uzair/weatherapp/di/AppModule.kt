package com.uzair.weatherapp.di

import android.content.Context
import com.uzair.weatherapp.BuildConfig
import com.uzair.weatherapp.service.WeatherRepository
import com.uzair.weatherapp.service.WeatherService
import com.uzair.weatherapp.ui.ProgressDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import dagger.assisted.AssistedFactory


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): WeatherService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.OPEN_WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
            .create(WeatherService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepo(): WeatherRepository {
        return WeatherRepository(provideRetrofit())
    }

    @Singleton
    @Provides
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(provideLogger()).build()
    }

    @AssistedFactory
    interface ProgressDialogFactory {
        fun create(context: Context): ProgressDialog
    }


}