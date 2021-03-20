package com.example.weatherapp.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityWeatherBinding


class MainActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityWeatherBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_weather
        )
        //nav graph is used to handle navigation from one fragment to another. This way we can remove the bioler plate code for
        // fragment navigation
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        )
    }

    /**
     * This is to handle back button click
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}