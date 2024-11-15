package com.example.ecobee_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.math.floor

@Composable
fun BackArrow(
    navController: NavHostController,
    onClick: (() -> Unit)? = null
) {

    Box(
        modifier = Modifier
            .fillMaxWidth().padding(15.dp),
        contentAlignment = Alignment.TopStart
    ) {
        IconButton(onClick = {
            onClick?.invoke() ?: navController.popBackStack()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(30.dp)

            )
        }
    }

}

@Composable
fun ModeButton(
    mode: String,
    selectedMode: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(200.dp)
            .height(70.dp),
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.White),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selectedMode == mode) Color.White else Color.Black,
            contentColor = if (selectedMode == mode) Color.Black else Color.White
        ),
        elevation = ButtonDefaults.buttonElevation(2.dp)
    ) {
        Text(text = mode, fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
}


//@Composable
//fun TemperatureDisplay(
//    temperature: Double,
//    onClick: (() -> Unit)? = null,
//    size: Int = 170,
//    backgroundColor: Color = Color.Black,
//    textColor: Color = Color.White
//) {
//    Surface(
//        color = backgroundColor,
//        shape = CircleShape,
//        modifier = Modifier
//            .size(size.dp)
//            .clickable(enabled = onClick != null) { onClick?.invoke() }
//    ) {
//        Box(contentAlignment = Alignment.Center) {
//            Text(
//                text = formatTemperature(temperature),
//                fontSize = (size).sp,
//                color = textColor
//            )
//        }
//    }
//}

// Helper function to format temperature with superscript for 0.5 values
fun formatTemperature(temp: Double): String {
    val wholeNumber = floor(temp).toInt()
    return if (temp % 1 == 0.5) {
        "$wholeNumber\u2075" // \u2075 is a superscript of 5
    } else {
        wholeNumber.toString()
    }
}
