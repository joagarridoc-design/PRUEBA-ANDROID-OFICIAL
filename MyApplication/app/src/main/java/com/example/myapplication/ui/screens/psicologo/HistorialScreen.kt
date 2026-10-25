package com.example.myapplication.ui.screens.psicologo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.BottomBarPsicologo
import com.example.myapplication.ui.theme.*

data class RegistroHistorial(
    val id: String,
    val estudiante: String,
    val curso: String,
    val instrumento: String,
    val fechaTermino: String,
    val nivelRiesgo: String // "Normal", "Atención Requerida", "Prioritario"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistorialScreen(
    onNavegar: (String) -> Unit = {}
) {
    var textoBusqueda by remember { mutableStateOf("") }

    val registros = listOf(
        RegistroHistorial("1", "Lucas Morales", "5° Básico A", "Escala de Autoconcepto Académico", "21/10/2026", "Normal"),
        RegistroHistorial("2", "Camila Castro", "2° Básico C", "Cuestionario de Bienestar Escolar", "20/10/2026", "Atención Requerida"),
        RegistroHistorial("3", "Tomás Vera", "6° Básico B", "Inventario de Clima de Aula", "18/10/2026", "Normal"),
        RegistroHistorial("4", "Valentina Ortiz", "4° Básico A", "Cuestionario de Bienestar Escolar", "15/10/2026", "Prioritario")
    )

    val registrosFiltrados = registros.filter {
        it.estudiante.contains(textoBusqueda, ignoreCase = true) ||
                it.curso.contains(textoBusqueda, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Historial de Evaluaciones",
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoBlanco)
            )
        },
        bottomBar = {
            BottomBarPsicologo(
                rutaActual = "historial",
                onNavegar = onNavegar
            )
        },
        containerColor = FondoBlanco
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Buscador
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                placeholder = { Text("Buscar estudiante o curso...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Buscar", tint = AzulPrimario) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AzulPrimario,
                    unfocusedBorderColor = BordeInput,
                    focusedTextColor = TextoNegro,
                    unfocusedTextColor = TextoNegro
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(registrosFiltrados) { reg ->
                    TarjetaHistorial(item = reg)
                }
            }
        }
    }
}

@Composable
private fun TarjetaHistorial(item: RegistroHistorial) {
    val (colorBadge, colorTexto) = when (item.nivelRiesgo) {
        "Prioritario" -> Pair(Color(0xFFFEE2E2), Color(0xFFB91C1C))
        "Atención Requerida" -> Pair(Color(0xFFFEF08A), Color(0xFF854D0E))
        else -> Pair(Color(0xFFDCFCE7), Color(0xFF15803D))
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.estudiante,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoNegro
                )

                Surface(
                    color = colorBadge,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = item.nivelRiesgo,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorTexto
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${item.curso} · Realizado el ${item.fechaTermino}",
                fontSize = 13.sp,
                color = TextoGrisOscuro
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = item.instrumento,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = AzulPrimario
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistorialScreenPreview() {
    HistorialScreen()
}

