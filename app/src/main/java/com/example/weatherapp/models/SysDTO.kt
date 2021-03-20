package com.example.weatherapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SysDTO(
    var pod: String
) : Parcelable
