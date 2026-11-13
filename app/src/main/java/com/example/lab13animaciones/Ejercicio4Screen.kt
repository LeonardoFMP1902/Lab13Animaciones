package com.example.lab13animaciones

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class EstadoUI {
    CARGANDO, CONTENIDO, ERROR
}

@Composable
fun Ejercicio4Screen() {
    var estadoActual by remember { mutableStateOf(EstadoUI.CARGANDO) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Contenedor animado que cambia según el estado
        AnimatedContent(
            targetState = estadoActual,
            transitionSpec = {
                fadeIn(animationSpec = tween(500)) togetherWith fadeOut(animationSpec = tween(500))
            },
            label = "ContentStateAnimation"
        ) { estado ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                colors = CardDefaults.cardColors(
                    containerColor = when (estado) {
                        EstadoUI.CARGANDO -> Color(0xFFE3F2FD)
                        EstadoUI.CONTENIDO -> Color(0xFFE8F5E9)
                        EstadoUI.ERROR -> Color(0xFFFFEBEE)
                    }
                )
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    when (estado) {
                        EstadoUI.CARGANDO -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Cargando datos...", fontWeight = FontWeight.Bold)
                        }
                        EstadoUI.CONTENIDO -> Text(
                            "✅ ¡Datos cargados con éxito!",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF2E7D32)
                        )
                        EstadoUI.ERROR -> Text(
                            "❌ Error de conexión al servidor",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC62828)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botones para alternar entre los 3 estados
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { estadoActual = EstadoUI.CARGANDO }) { Text("Cargando") }
            Button(onClick = { estadoActual = EstadoUI.CONTENIDO }) { Text("Contenido") }
            Button(onClick = { estadoActual = EstadoUI.ERROR }) { Text("Error") }
        }
    }
}