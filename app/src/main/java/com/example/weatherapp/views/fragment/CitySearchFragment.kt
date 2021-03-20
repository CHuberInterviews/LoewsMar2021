package com.example.weatherapp.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCitySearchBinding
import com.example.weatherapp.views.viewmodel.WeatherViewModel

class CitySearchFragment : Fragment() {

    private val viewModel by activityViewModels<WeatherViewModel>()

    private lateinit var binding: FragmentCitySearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCitySearchBinding.inflate(layoutInflater)
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        viewModel.navigateToWeatherListView.observe(viewLifecycleOwner) {
            if (it == true) {
                findNavController().navigate(R.id.action_citySearchFragment_to_weatherListFragment)
                viewModel.navigateToWeatherListView.value = false
            }
            viewModel.progressBarVisibility.value = false
        }

        viewModel.cityName.observe(viewLifecycleOwner) {
            viewModel.progressBarVisibility.value = false
            viewModel.liveDataCitySearchError.value = ""
        }
    }
}