package com.example.lab13animaciones

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EjercicioFinalScreen() {
    var saltando by remember { mutableStateOf(false) }
    var modoPowerUp by remember { mutableStateOf(false) }
    var modoNoche by remember { mutableStateOf(false) }
    var puntos by remember { mutableIntStateOf(0) }
    var mostrarBannerVictoria by remember { mutableStateOf(false) }

    // 1. Animación de color del escenario (Día / Noche)
    val colorCielo by animateColorAsState(
        targetValue = if (modoNoche) Color(0xFF1A237E) else Color(0xFF81D4FA),
        animationSpec = tween(1000),
        label = "SkyColor"
    )

    // 2. Animación de posición vertical (Salto)
    val alturaSalto by animateDpAsState(
        targetValue = if (saltando) (-110).dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "JumpOffset"
    )

    // 3. Animación de tamaño (Power-Up)
    val tamanoHeroe by animateDpAsState(
        targetValue = if (modoPowerUp) 95.dp else 65.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy),
        label = "HeroSize"
    )

    // 4. Animación de color del héroe
    val colorHeroe by animateColorAsState(
        targetValue = if (modoPowerUp) Color(0xFFFFD600) else Color(0xFFE53935),
        label = "HeroColor"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorCielo)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // HUD Superior con AnimatedContent para el puntaje
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.Black.copy(alpha = 0.6f))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("🛡️ HERO QUEST", color = Color.White, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Puntos: ", color = Color.White)
                AnimatedContent(
                    targetState = puntos,
                    transitionSpec = {
                        slideInVertically { -it } + fadeIn() togetherWith slideOutVertically { it } + fadeOut()
                    },
                    label = "ScoreAnim"
                ) { score ->
                    Text(
                        text = "$score",
                        color = Color(0xFFFFD600),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                }
            }
        }

        // 5. Banner animado con AnimatedVisibility
        AnimatedVisibility(
            visible = mostrarBannerVictoria,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut()
        ) {
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50))) {
                Text(
                    text = "🏆 ¡NIVEL COMPLETADO! 🏆",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        // Zona de juego (Personaje + Suelo)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .offset(y = alturaSalto)
                    .size(tamanoHeroe)
                    .clip(CircleShape)
                    .background(colorHeroe),
                contentAlignment = Alignment.Center
            ) {
                Text(text = if (modoPowerUp) "⚡" else "🦸", fontSize = 30.sp)
            }

            // Suelo del escenario
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF388E3C))
            )
        }

        // Controles del Videojuego
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        saltando = !saltando
                        if (saltando) puntos += 10
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (saltando) "Bajar" else "Saltar (+10)")
                }

                Button(
                    onClick = {
                        modoPowerUp = !modoPowerUp
                        if (modoPowerUp) puntos += 50
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (modoPowerUp) "Normal" else "Power-Up")
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { modoNoche = !modoNoche },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (modoNoche) "☀️ Día" else "🌙 Noche")
                }

                Button(
                    onClick = { mostrarBannerVictoria = !mostrarBannerVictoria },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Victoria")
                }
            }
        }
    }
}