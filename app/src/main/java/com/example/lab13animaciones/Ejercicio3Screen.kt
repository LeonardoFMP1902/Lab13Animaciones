package com.example.lab13animaciones

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
fun Ejercicio3Screen() {
    var expandidoYMovido by remember { mutableStateOf(false) }

    // Animación de tamaño
    val tamanoAnimado by animateDpAsState(
        targetValue = if (expandidoYMovido) 180.dp else 100.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "SizeAnimation"
    )

    // Animación de posición en el eje X
    val desplazamientoX by animateDpAsState(
        targetValue = if (expandidoYMovido) 60.dp else (-60).dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = "OffsetAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // IMPORTANTE: offset va ANTES de size y background para mover todo el cuadro
        Box(
            modifier = Modifier
                .offset(x = desplazamientoX, y = 0.dp)
                .size(tamanoAnimado)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFF5722)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Mover/Escalar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { expandidoYMovido = !expandidoYMovido }) {
            Text(text = "Animar Tamaño y Posición")
        }
    }
}