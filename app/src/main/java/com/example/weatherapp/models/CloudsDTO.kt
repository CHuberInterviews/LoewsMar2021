package com.example.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CloudsDTO(
    var all: Int?
) : Parcelable