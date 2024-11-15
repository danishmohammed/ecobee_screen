package com.example.ecobee_screen

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.setValue

class ThermostatViewModel : ViewModel() {
    var selectedMode by mutableStateOf("Heat")
    var desiredTemp by mutableDoubleStateOf(22.5)
    var currentTemp by mutableDoubleStateOf(22.5)
    var isHoldingRowVisible by mutableStateOf(true)

    // When setting the mode from the SelectModeScreen composable
    fun setMode(mode: String) {
        selectedMode = mode

        // if cooling or heating, show the holding row
        if (mode == "Heat" || mode == "Cool") {
            isHoldingRowVisible = true
        } else {
            isHoldingRowVisible = false
        }
    }

    // When it is changed from the AdjustTemperatureScreen composable
    fun updateDesiredTemp(temp: Double) {
        desiredTemp = temp
    }

    // When cancel button is clicked on holding bar
    fun resetTemp() {
        desiredTemp = currentTemp
        isHoldingRowVisible = false
    }

}
