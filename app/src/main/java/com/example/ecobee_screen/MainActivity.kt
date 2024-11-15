package com.example.ecobee_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: ThermostatViewModel by viewModels()

        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "thermostat") {
                composable("thermostat") {
                    ThermostatScreen(navController = navController, viewModel = viewModel)
                }
                composable("select_mode") {
                    SelectModeScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
                composable("adjust_temperature") {
                    AdjustTemperatureScreen(
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
