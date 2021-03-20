package com.example.weatherapp.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherListBinding
import com.example.weatherapp.views.adapter.WeatherListAdapter
import com.example.weatherapp.views.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*


class ListFragment : Fragment() {

    val viewModel by activityViewModels<WeatherViewModel>()

    private lateinit var binding: FragmentWeatherListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherListBinding.inflate(layoutInflater)
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        requireActivity().toolbar.title = viewModel.cityName.value?.toUpperCase()

        viewModel.adapter = WeatherListAdapter {
            viewModel.setSelectedWeather(it)
            findNavController().navigate(R.id.action_weatherListFragment_to_weatherDetailsFragment)
        }
    }

}