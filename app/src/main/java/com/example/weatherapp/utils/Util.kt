package com.example.weatherapp.utils

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.views.adapter.CustomRecyclerViewAdapter
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@BindingAdapter("android:converterInt")
fun convertToInt(view: TextView, value: Double) {
    view.text = value.toString()
}


@BindingAdapter("app:adapter", "app:data")
fun <T : CustomRecyclerViewAdapter<*, *>> bind(
    recyclerView: RecyclerView,
    adapter: T,
    data: ArrayList<Nothing>
) {
    recyclerView.adapter = adapter
    adapter.updateData(data)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

fun getStringResponseFromRaw(response: ResponseBody): String? {
    var reader: BufferedReader? = null
    val sb = StringBuilder()
    try {
        reader = BufferedReader(InputStreamReader(response.byteStream()))
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return sb.toString()
}
