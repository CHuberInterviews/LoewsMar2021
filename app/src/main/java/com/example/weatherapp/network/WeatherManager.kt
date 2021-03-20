package com.example.weatherapp.network

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.network.responseDTO.WeatherResponseDTO
import com.example.weatherapp.utils.GET_WEATHER
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

class WeatherManager {
    private val service: WeatherService
    private val retrofit = RetrofitService.providesRetrofit()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    fun getWeather(cityName: String) = service.getWeather(cityName)

    interface WeatherService {
        @GET(GET_WEATHER)
        fun getWeather(
            @Query("q") cityName: String,
            @Query("appid") appId: String = BuildConfig.SECRET_KEY,
            @Query("units") metric: String = "metric"
        ): Single<WeatherResponseDTO>
    }
}