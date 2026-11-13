package com.example.lab13animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lab13animaciones.ui.theme.Lab13AnimacionesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab13AnimacionesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MenuPrincipalAnimaciones()
                }
            }
        }
    }
}

@Composable
fun MenuPrincipalAnimaciones() {
    var pestanaSeleccionada by remember { mutableIntStateOf(0) }
    val titulos = listOf("Ej 1", "Ej 2", "Ej 3", "Ej 4", "Juego")

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = pestanaSeleccionada) {
                titulos.forEachIndexed { index, titulo ->
                    Tab(
                        selected = pestanaSeleccionada == index,
                        onClick = { pestanaSeleccionada = index },
                        text = { Text(titulo) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (pestanaSeleccionada) {
                0 -> Ejercicio1Screen()
                1 -> Ejercicio2Screen()
                2 -> Ejercicio3Screen()
                3 -> Ejercicio4Screen()
                4 -> EjercicioFinalScreen()
            }
        }
    }
}