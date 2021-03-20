package com.example.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDetailsDTO(
        var dt: Long?,
        var main: MainDTO?,
        var weather: List<WeatherDTO>?,
        var clouds: CloudsDTO?,
        var wind: WindDTO?,
        var visibility: Double?,
        var pop: Double?,
        var sys: SysDTO?,
        var dt_txt: String?
) : Parcelable