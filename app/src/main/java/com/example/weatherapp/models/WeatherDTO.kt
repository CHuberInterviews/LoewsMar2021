package com.example.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherDTO(
    var id: Int,
    var main: String?,
    var description: String?,
    var icon: String?
) : Parcelable