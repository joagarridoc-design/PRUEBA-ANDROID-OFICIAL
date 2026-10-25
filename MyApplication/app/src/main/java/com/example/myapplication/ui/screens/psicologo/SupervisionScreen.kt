package com.example.myapplication.ui.screens.psicologo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
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

data class SesionActiva(
    val id: String,
    val estudiante: String,
    val curso: String,
    val codigo: String,
    val progresoPreguntas: String,
    val porcentaje: Float,
    val tiempoTranscurrido: String,
    val enLinea: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupervisionScreen(
    onNavegar: (String) -> Unit = {},
    onVolverClick: () -> Unit = {}
) {
    val sesiones = listOf(
        SesionActiva("1", "Sofía Rojas", "3° Básico B", "918234", "6 / 10", 0.6f, "4 min", true),
        SesionActiva("2", "Mateo Silva", "4° Básico A", "482910", "1 / 10", 0.1f, "1 min", true),
        SesionActiva("3", "Benjamín Soto", "2° Básico A", "554129", "0 / 10", 0.0f, "Esperando inicio", false)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Supervisión en Vivo",
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVolverClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = AzulPrimario
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Actualizar",
                            tint = AzulPrimario
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoBlanco)
            )
        },
        bottomBar = {
            BottomBarPsicologo(
                rutaActual = "panel_principal",
                onNavegar = onNavegar
            )
        },
        containerColor = FondoBlanco
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2FE)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "👀 Monitoreo en tiempo real de los alumnos que están respondiendo en este momento.",
                            fontSize = 13.sp,
                            color = AzulPrimario,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            items(sesiones) { sesion ->
                TarjetaSesionSupervisada(sesion = sesion)
            }
        }
    }
}

@Composable
private fun TarjetaSesionSupervisada(sesion: SesionActiva) {
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
                    text = sesion.estudiante,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoNegro
                )

                Surface(
                    color = if (sesion.enLinea) Color(0xFFDCFCE7) else Color(0xFFFEF08A),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = if (sesion.enLinea) "● En Vivo" else "En Espera",
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (sesion.enLinea) Color(0xFF15803D) else Color(0xFF854D0E)
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${sesion.curso} · Código: ${sesion.codigo}",
                fontSize = 13.sp,
                color = TextoGrisOscuro
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Progreso: ${sesion.progresoPreguntas}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AzulPrimario
                )
                Text(
                    text = "Tiempo: ${sesion.tiempoTranscurrido}",
                    fontSize = 13.sp,
                    color = TextoGrisOscuro
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            LinearProgressIndicator(
                progress = { sesion.porcentaje },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = AzulPrimario,
                trackColor = Color(0xFFE2E8F0)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupervisionScreenPreview() {
    SupervisionScreen()
}

