package com.example.weatherapp.views.adapter

import androidx.recyclerview.widget.RecyclerView


abstract class CustomRecyclerViewAdapter<T, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    abstract fun updateData(data: List<T>)
}
