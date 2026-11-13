package com.example.lab13animaciones

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Ejercicio2Screen() {
    var esAzul by remember { mutableStateOf(true) }

    // Animación de color usando tween
    val colorAnimado by animateColorAsState(
        targetValue = if (esAzul) Color(0xFF2196F3) else Color(0xFF4CAF50),
        animationSpec = tween(durationMillis = 800),
        label = "ColorBoxAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(colorAnimado),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (esAzul) "Azul" else "Verde",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { esAzul = !esAzul }) {
            Text(text = "Cambiar Color")
        }
    }
}