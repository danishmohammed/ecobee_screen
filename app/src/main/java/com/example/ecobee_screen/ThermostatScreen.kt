package com.example.ecobee_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val archivioFontFamily = FontFamily(Font(R.font.archivio_regular))
val orange = Color(0xFFFFA500)

@Composable
fun ThermostatScreen(navController: NavHostController, viewModel: ThermostatViewModel) {
    val isHoldingRowVisible = viewModel.isHoldingRowVisible
    val selectedMode = viewModel.selectedMode
    val desiredTemp = viewModel.desiredTemp
    val currentTemp = viewModel.currentTemp
    val modeColor = if (selectedMode == "Cool") Color.Cyan else orange
    val dynamicColor = when {
        desiredTemp > currentTemp && selectedMode == "Heat" -> orange
        desiredTemp < currentTemp && selectedMode == "Cool" -> Color.Cyan
        else -> Color.White
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top row with icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {})
                {
                    Icon(
                        painter = painterResource(id = R.drawable.partly_cloudy_day),
                        contentDescription = "Weather Icon",
                        tint = Color.White,
                        modifier = Modifier.size(34.dp)
                    )
                }

                IconButton(onClick = {
                    navController.navigate("select_mode")
                })
                {
                    Icon(
                        painter = painterResource(id = R.drawable.heat),
                        contentDescription = "Heat Icon",
                        tint = dynamicColor,
                        modifier = Modifier.size(34.dp)
                    )
                }

                IconButton(onClick = {})
                {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu Icon",
                        tint = Color.White,
                        modifier = Modifier.size(34.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(0.5f))

            // Humidity display row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.water_drop),
                    contentDescription = "Water Drop Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Cyan
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "48%", // Placeholder
                    fontSize = 25.sp,
                    color = Color.Cyan
                )
            }

            Spacer(modifier = Modifier.weight(0.2f))

            Text(
                text = formatTemperature(currentTemp),
                fontSize = 170.sp,
                fontFamily = archivioFontFamily,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                    navController.navigate("adjust_temperature")
                }
            )

            Spacer(modifier = Modifier.weight(0.5f))

            // Holding row (dynamic based on ViewModel state)
            if (isHoldingRowVisible) {
                Surface(
                    color = Color.Black,
                    shape = RoundedCornerShape(66.dp),
                    border = BorderStroke(2.dp, modeColor),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .height(65.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(text = formatTemperature(desiredTemp),
                            fontSize = 22.sp, color = modeColor)
                        Text(text = " | Holding", fontSize = 22.sp, color = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        IconButton(onClick = {
                            viewModel.resetTemp()
                        })
                        {
                            Icon(
                                painter = painterResource(id = R.drawable.cancel),
                                contentDescription = "Cancel Icon",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            } else {
                Surface(
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .height(65.dp)
                ) {}
            }
            Spacer(modifier = Modifier.weight(0.5f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThermostatScreen() {
    val navController = rememberNavController()
    val viewModel = ThermostatViewModel()

    // initial values for preview testing
    viewModel.setMode("Heat")
    viewModel.currentTemp = 23.5
    viewModel.desiredTemp = 25.5

    ThermostatScreen(navController = navController, viewModel = viewModel)
}
