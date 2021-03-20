package com.example.weatherapp.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.models.MainDTO
import com.example.weatherapp.models.WeatherDetailsDTO
import com.example.weatherapp.network.WeatherManager
import com.example.weatherapp.network.responseDTO.WeatherResponseDTO
import com.example.weatherapp.utils.RxSingleSchedulers
import com.example.weatherapp.utils.getStringResponseFromRaw
import com.example.weatherapp.views.adapter.WeatherListAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * The main view model for the app. In this case since there is one requirement. We have one view model
 * This view model is shared among all the fragment, so that the data does not need to be transferred from one fragment to
 * another fragment
 */
class WeatherViewModel : ViewModel() {
    val weatherList: ArrayList<WeatherDetailsDTO> = ArrayList()
    var adapter: WeatherListAdapter? = null
    private var disposable: CompositeDisposable? = CompositeDisposable()
    private var _selectedWeatherData: MutableLiveData<WeatherDetailsDTO> = MutableLiveData()
    var selectedWeatherData: LiveData<WeatherDetailsDTO>? = _selectedWeatherData

    var cityName: MutableLiveData<String> = MutableLiveData()
    var liveDataCitySearchError: MutableLiveData<String> = MutableLiveData()
    val navigateToWeatherListView: MutableLiveData<Boolean> = MutableLiveData()
    var progressBarVisibility: MutableLiveData<Boolean> = MutableLiveData()

    private var rxSingleSchedulers: RxSingleSchedulers = RxSingleSchedulers.DEFAULT

    /**
     * Search city
     */
    fun searchCity() {
        if (cityName.value?.isNotBlank() == true) {
            progressBarVisibility.value = true
            disposable?.add(
                WeatherManager().getWeather(cityName.value ?: "")
                    .compose(rxSingleSchedulers.applySchedulers())
                    .subscribe(this::onSuccess, this::onFailure)
            )
        } else {
            liveDataCitySearchError.postValue("Enter City Name")
        }
    }

    private fun onSuccess(weatherResponseDTO: WeatherResponseDTO){
        progressBarVisibility.value = false
        weatherList.clear()
        weatherResponseDTO.list.map { it.copy(main = convertToFahrenheit(it.main!!)) }
            .also {
                weatherList.addAll(ArrayList(it))
            }
        navigateToWeatherListView.postValue(true)
    }
    private fun onFailure(error: Throwable){
        liveDataCitySearchError.postValue(error.message ?: "Failed To Search")
        progressBarVisibility.value = false
    }
    fun setSelectedWeather(weatherDetailsDTO: WeatherDetailsDTO) {
        _selectedWeatherData.value = weatherDetailsDTO
    }

    // converts {@link MainDTO} celsius to fahrenheit
    private fun convertToFahrenheit(mainWeatherDto: MainDTO): MainDTO {
        return mainWeatherDto.copy(
            temp = calculateFahrenheit(mainWeatherDto.temp!!),
            feels_like = calculateFahrenheit(mainWeatherDto.feels_like!!)
        )
    }

    private fun calculateFahrenheit(degrees: Double): Double {
        val degreesInFahrenheit = (degrees * 1.8) + 32
        return BigDecimal(degreesInFahrenheit).setScale(0, RoundingMode.DOWN).toDouble()
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.run {
            clear()
            null
        }
    }
}