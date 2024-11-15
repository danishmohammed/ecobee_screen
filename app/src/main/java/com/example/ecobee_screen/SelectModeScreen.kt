package com.example.ecobee_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun SelectModeScreen(
    navController: NavHostController,
    viewModel: ThermostatViewModel
) {

    val selectedMode = viewModel.selectedMode

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {

        BackArrow(navController = navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Spacer(modifier = Modifier.height(50.dp))

            listOf("Heat", "Cool", "Auto", "Off").forEach { mode ->
                ModeButton(
                    mode = mode,
                    selectedMode = selectedMode,
                    onClick = {
                        viewModel.setMode(mode)
                        navController.popBackStack()
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelectModeScreen() {
    val navController = rememberNavController()
    val viewModel = ThermostatViewModel()

    SelectModeScreen(
        navController = navController,
        viewModel = viewModel
    )
}

