package com.example.myapplication.ui.screens.estudiante

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.myapplication.ui.theme.*

@Composable
fun ResponderScreen(
    onFinalizarClick: () -> Unit = {},
    onVolverAInstrucciones: () -> Unit = {}
) {
    // Lista de las 10 preguntas simuladas para la demo del caso
    val preguntas = remember {
        listOf(
            "Me gusta participar en las actividades de mi curso.",
            "Siento que mis compañeros y profesores me escuchan.",
            "Me siento cómodo expresando lo que pienso en clases.",
            "Me gusta trabajar en equipo con otros estudiantes.",
            "Siento que mi escuela es un lugar seguro y acogedor.",
            "Cuando tengo problemas, pido ayuda a los profesores.",
            "Me da entusiasmo aprender cosas nuevas cada día.",
            "Respeto las opiniones de mis compañeros de curso.",
            "Me siento integrado y valorado en la escuela.",
            "En general, me siento feliz cuando estoy en la escuela."
        )
    }

    var indicePreguntaActual by remember { mutableIntStateOf(0) }
    val respuestasElegidas = remember { mutableStateMapOf<Int, Int>() }
    val opciones = listOf("Nunca", "A veces", "Frecuentemente", "Siempre")

    val preguntaTexto = preguntas[indicePreguntaActual]
    val respuestaSeleccionada = respuestasElegidas[indicePreguntaActual] ?: -1
    val esUltimaPregunta = indicePreguntaActual == preguntas.size - 1

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoBlanco
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                // Header de progreso dinámico
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tu avance",
                        fontSize = 14.sp,
                        color = TextoGrisOscuro
                    )
                    Text(
                        text = "${indicePreguntaActual + 1} de ${preguntas.size}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Barra de progreso dinámica
                LinearProgressIndicator(
                    progress = { (indicePreguntaActual + 1) / preguntas.size.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = AzulPrimario,
                    trackColor = Color(0xFFE2E8F0)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Tarjeta de Pregunta dinámicas
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2FE)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "PREGUNTA ${indicePreguntaActual + 1}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulPrimario
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = preguntaTexto,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoNegro
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Elige una respuesta:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextoGrisOscuro
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Opciones de respuesta
                opciones.forEachIndexed { index, opcion ->
                    val estaSeleccionado = respuestaSeleccionada == index
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { respuestasElegidas[indicePreguntaActual] = index },
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (estaSeleccionado) Color(0xFFF0F9FF) else FondoBlanco
                        ),
                        border = androidx.compose.foundation.BorderStroke(
                            width = if (estaSeleccionado) 2.dp else 1.dp,
                            color = if (estaSeleccionado) AzulPrimario else BordeInput
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = estaSeleccionado,
                                onClick = { respuestasElegidas[indicePreguntaActual] = index },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = AzulPrimario,
                                    unselectedColor = BordeInput
                                )
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = opcion,
                                fontSize = 16.sp,
                                fontWeight = if (estaSeleccionado) FontWeight.Bold else FontWeight.Normal,
                                color = TextoNegro
                            )
                        }
                    }
                }
            }

            // Botones de Navegación
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        if (indicePreguntaActual > 0) {
                            indicePreguntaActual--
                        } else {
                            onVolverAInstrucciones()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = AzulPrimario)
                ) {
                    Text("← Anterior", fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        if (esUltimaPregunta) {
                            onFinalizarClick()
                        } else {
                            indicePreguntaActual++
                        }
                    },
                    enabled = respuestaSeleccionada != -1,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulPrimario,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        if (esUltimaPregunta) "Finalizar ✓" else "Siguiente →",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResponderScreenPreview() {
    ResponderScreen()
}