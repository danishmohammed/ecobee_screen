package com.example.ecobee_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun AdjustTemperatureScreen(
    navController: NavHostController,
    viewModel: ThermostatViewModel
) {
    // Tracking by the mutable state means that if the viewModel.variable changes in the
    // background, we re-render the UI accordingly
    var desiredTemp by remember { mutableDoubleStateOf(viewModel.desiredTemp) }
    val mode by remember {mutableStateOf(viewModel.selectedMode)}

    // The color for the background of the temp display
    val backColor = when (mode) {
        "Heat" -> orange
        "Cool" -> Color.Blue
        else -> Color.Black
    }

    // Adjust the temperature display with some animation
    val animatedTemp by animateFloatAsState(targetValue = desiredTemp.toFloat())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {

        BackArrow(
            navController = navController,
            onClick = {
                viewModel.updateDesiredTemp(desiredTemp)
                navController.popBackStack()
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                
                // Dummy icon so that our temp display will be centered
                Icon(
                    painter = painterResource(id = R.drawable.add_circle),
                    contentDescription = "Increase Temperature",
                    tint = Color.Black,
                    modifier = Modifier.size(70.dp)
                )

                // Temperature Display
                Surface(
                    color = backColor,
                    shape = RoundedCornerShape(66.dp),
                    modifier = Modifier.size(170.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = formatTemperature(animatedTemp.toDouble()),
                            fontSize = 78.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(20.dp))

                // Increase and Decrease Buttons vertically stacked on the right
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            // Max temp is 35 C
                            if (desiredTemp < 35) {
                                desiredTemp += 0.5
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Increase Temperature",
                            tint = Color.White,
                            modifier = Modifier.size(80.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    IconButton(
                        onClick = {
                            // Min temp is 5 C (it's Canada, why would you go lower??)
                            if (desiredTemp > 5) {
                                desiredTemp -= 0.5
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus_circle),
                            contentDescription = "Decrease Temperature",
                            tint = Color.White,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAdjustTemperatureScreen() {
    val navController = rememberNavController()
    val viewModel = ThermostatViewModel()
    viewModel.updateDesiredTemp(22.5)

    AdjustTemperatureScreen(navController, viewModel)
}
