package com.example.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WindDTO(
    var speed: Double?,
    var deg: Int
) : Parcelable
