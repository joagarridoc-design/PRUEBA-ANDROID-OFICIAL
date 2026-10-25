package com.example.myapplication.ui.screens.estudiante


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.*

@Composable
fun InstruccionesScreen(
    onComenzarClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding(),
        color = FondoBlanco
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Antes de comenzar",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulPrimario
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Bienestar en la escuela",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoNegro
                )

                Text(
                    text = "Actividad de demostración · 10 preguntas",
                    fontSize = 14.sp,
                    color = TextoGrisOscuro
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tarjeta de indicaciones
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "¿Qué tienes que hacer?",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoNegro
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        ItemInstruccion(numero = "1", texto = "Lee cada frase con calma.")
                        Spacer(modifier = Modifier.height(12.dp))
                        ItemInstruccion(numero = "2", texto = "Marca la opción que más se parece a lo que piensas.")
                        Spacer(modifier = Modifier.height(12.dp))
                        ItemInstruccion(numero = "3", texto = "Si tienes dudas, pregunta a la persona que te acompaña.")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tiempo estimado
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "⏱ Tiempo estimado: ",
                            fontSize = 14.sp,
                            color = TextoGrisOscuro
                        )
                        Text(
                            text = "8 minutos",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoNegro
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color(0xFF16A34A)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "No hay respuestas buenas o malas.",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextoNegro
                    )
                }
            }

            // Botón Comenzar
            Button(
                onClick = onComenzarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrimario,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Comenzar →",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun ItemInstruccion(numero: String, texto: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(Color(0xFFE0F2FE), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = numero,
                color = AzulPrimario,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = texto,
            fontSize = 14.sp,
            color = TextoNegro
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InstruccionesScreenPreview() {
    InstruccionesScreen()
}
