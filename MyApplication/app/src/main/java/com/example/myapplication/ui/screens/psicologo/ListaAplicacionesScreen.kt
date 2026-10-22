package com.example.myapplication.ui.screens.psicologo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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

data class AplicacionItem(
    val id: String,
    val estudiante: String,
    val curso: String,
    val instrumento: String,
    val codigo: String,
    val fecha: String,
    val estado: String // "Pendiente", "En desarrollo", "Finalizada"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaAplicacionesScreen(
    onNavegar: (String) -> Unit = {}
) {
    var filtroSeleccionado by remember { mutableStateOf("Todas") }
    val filtros = listOf("Todas", "Pendientes", "En desarrollo", "Finalizadas")

    val listaAplicaciones = listOf(
        AplicacionItem("1", "Mateo Silva", "4° Básico A", "Cuestionario de Bienestar Escolar", "482910", "22/10/2026", "Pendiente"),
        AplicacionItem("2", "Sofía Rojas", "3° Básico B", "Cuestionario de Bienestar Escolar", "918234", "22/10/2026", "En desarrollo"),
        AplicacionItem("3", "Lucas Morales", "5° Básico A", "Escala de Autoconcepto Académico", "104928", "21/10/2026", "Finalizada"),
        AplicacionItem("4", "Camila Castro", "2° Básico C", "Cuestionario de Bienestar Escolar", "736152", "20/10/2026", "Finalizada")
    )

    val aplicacionesFiltradas = when (filtroSeleccionado) {
        "Pendientes" -> listaAplicaciones.filter { it.estado == "Pendiente" }
        "En desarrollo" -> listaAplicaciones.filter { it.estado == "En desarrollo" }
        "Finalizadas" -> listaAplicaciones.filter { it.estado == "Finalizada" }
        else -> listaAplicaciones
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Gestión de Aplicaciones",
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoBlanco)
            )
        },
        bottomBar = {
            BottomBarPsicologo(
                rutaActual = "lista_aplicaciones",
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

            // Chips de filtrado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filtros.forEach { filtro ->
                    val seleccionado = filtroSeleccionado == filtro
                    FilterChip(
                        selected = seleccionado,
                        onClick = { filtroSeleccionado = filtro },
                        label = { Text(filtro, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AzulPrimario,
                            selectedLabelColor = Color.White,
                            containerColor = Color(0xFFF1F5F9),
                            labelColor = TextoNegro
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(aplicacionesFiltradas) { app ->
                    TarjetaAplicacion(item = app)
                }
            }
        }
    }
}

@Composable
private fun TarjetaAplicacion(item: AplicacionItem) {
    val (colorFondoBadge, colorTextoBadge) = when (item.estado) {
        "Pendiente" -> Pair(Color(0xFFFEF08A), Color(0xFF854D0E))
        "En desarrollo" -> Pair(Color(0xFFE0F2FE), Color(0xFF0369A1))
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
                    color = colorFondoBadge,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = item.estado,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorTextoBadge
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${item.curso} · ${item.fecha}",
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

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(color = BordeInput, thickness = 0.5.dp)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Código: ",
                        fontSize = 13.sp,
                        color = TextoGrisOscuro
                    )
                    Text(
                        text = item.codigo,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextoNegro
                    )
                }

                if (item.estado == "Finalizada") {
                    TextButton(onClick = { }) {
                        Text("Ver Respuestas", color = AzulPrimario, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaAplicacionesScreenPreview() {
    ListaAplicacionesScreen()
}