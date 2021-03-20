package com.example.weatherapp.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.models.WeatherDetailsDTO

import kotlinx.android.synthetic.main.weather_list_item.view.*

class WeatherListAdapter(private var onItemClick: (WeatherDetailsDTO) -> Unit) :
    CustomRecyclerViewAdapter<WeatherDetailsDTO, WeatherListAdapter.WeatherViewHolder>() {
    val weatherList: ArrayList<WeatherDetailsDTO> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
        WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item, parent, false),
            onItemClick
        )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) =
        holder.loadData(position)

    override fun getItemCount(): Int = weatherList.size

    inner class WeatherViewHolder(
        private val view: View,
        private val onClickItem: ((WeatherDetailsDTO) -> Unit?)?
    ) : RecyclerView.ViewHolder(view) {

        fun loadData(position: Int) {
            val data = weatherList[position]

            with(view) {
                weather_title.text = data.weather?.get(0)?.main ?: ""
                weather_temp.text = String.format(
                    view.context.getString(R.string.temperature_with_degrees),
                    data.main?.temp?.toString() ?: "0"
                )
                setOnClickListener {
                    onClickItem?.let { onClick -> onClick(data) }
                }
            }
        }

    }

    override fun updateData(data: List<WeatherDetailsDTO>) {
        this.weatherList.clear()
        if (data.isNotEmpty()) {
            this.weatherList.addAll(data)
        }
        notifyDataSetChanged()
    }
}