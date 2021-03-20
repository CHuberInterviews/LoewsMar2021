package com.example.weatherapp.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.databinding.FragmentWeatherDetailsBinding
import com.example.weatherapp.views.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*

class DetailsFragment : Fragment() {

    val viewModel by activityViewModels<WeatherViewModel>()

    private lateinit var binding: FragmentWeatherDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherDetailsBinding.inflate(layoutInflater)
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().toolbar.title = viewModel.cityName.value?.toUpperCase()
    }

}