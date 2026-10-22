package com.example.myapplication.ui.screens.psicologo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

data class RespuestaDetalle(
    val pregunta: String,
    val respuestaEstudiante: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RevisionScreen(
    onNavegar: (String) -> Unit = {},
    onVolverClick: () -> Unit = {}
) {
    var observaciones by remember { mutableStateOf("") }

    val preguntasYRespuestas = listOf(
        RespuestaDetalle("Me gusta participar en las actividades de mi curso.", "Siempre"),
        RespuestaDetalle("Siento que mis compañeros y profesores me escuchan.", "A veces"),
        RespuestaDetalle("Me siento cómodo expresando lo que pienso en clases.", "Frecuentemente"),
        RespuestaDetalle("Me gusta trabajar en equipo con otros estudiantes.", "Siempre"),
        RespuestaDetalle("Siento que mi escuela es un lugar seguro y acogedor.", "Frecuentemente")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Revisión de Evaluación",
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
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Lucas Morales",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextoNegro
                            )
                            Surface(
                                color = Color(0xFFDCFCE7),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(
                                    text = "Finalizada",
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF15803D)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Curso: 5° Básico A · Fecha: 21/10/2026", fontSize = 13.sp, color = TextoGrisOscuro)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text("Instrumento: Escala de Autoconcepto Académico", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = AzulPrimario)
                    }
                }
            }

            item {
                Text(
                    text = "Respuestas registradas",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoNegro
                )
            }

            itemsIndexed(preguntasYRespuestas) { index, item ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9))
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Pregunta ${index + 1}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulPrimario
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = item.pregunta,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextoNegro
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Respuesta: ", fontSize = 13.sp, color = TextoGrisOscuro)
                            Text(
                                text = item.respuestaEstudiante,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = AzulPrimario
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    text = "Observaciones del Psicólogo",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoNegro
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = observaciones,
                    onValueChange = { observaciones = it },
                    placeholder = { Text("Escribe aquí tus observaciones clínicas...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AzulPrimario,
                        unfocusedBorderColor = BordeInput,
                        focusedTextColor = TextoNegro,
                        unfocusedTextColor = TextoNegro
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { onVolverClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulPrimario,
                        contentColor = Color.White
                    )
                ) {
                    Text("Guardar Observaciones", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RevisionScreenPreview() {
    RevisionScreen()
}

