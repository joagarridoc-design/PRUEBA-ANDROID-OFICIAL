package com.example.myapplication.ui.screens.psicologo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.BottomBarPsicologo
import com.example.myapplication.ui.theme.*

data class Instrumento(
    val id: String,
    val titulo: String,
    val descripcion: String,
    val preguntas: Int,
    val tiempoEstimado: String,
    val nivel: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    onNavegar: (String) -> Unit = {},
    onAplicarInstrumento: (String) -> Unit = {}
) {
    val listaInstrumentos = listOf(
        Instrumento(
            id = "1",
            titulo = "Cuestionario de Bienestar Escolar",
            descripcion = "Evalúa la percepción del estudiante sobre la convivencia, seguridad y pertenencia en la escuela.",
            preguntas = 10,
            tiempoEstimado = "8 min",
            nivel = "1° a 4° Básico"
        ),
        Instrumento(
            id = "2",
            titulo = "Escala de Autoconcepto Académico",
            descripcion = "Mide la autovaloración del estudiante respecto a sus habilidades y motivación para el aprendizaje.",
            preguntas = 12,
            tiempoEstimado = "10 min",
            nivel = "5° a 8° Básico"
        ),
        Instrumento(
            id = "3",
            titulo = "Inventario de Clima de Aula y Emociones",
            descripcion = "Identifica factores de estrés y la relación del alumno con sus pares y equipo docente.",
            preguntas = 15,
            tiempoEstimado = "12 min",
            nivel = "Media / PIE"
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Catálogo de Instrumentos",
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoBlanco)
            )
        },
        bottomBar = {
            BottomBarPsicologo(
                rutaActual = "catalogo",
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
                Text(
                    text = "Selecciona un instrumento para iniciar una evaluación",
                    fontSize = 14.sp,
                    color = TextoGrisOscuro
                )
            }

            items(listaInstrumentos) { instrumento ->
                TarjetaInstrumento(
                    instrumento = instrumento,
                    onAplicar = { onAplicarInstrumento(instrumento.id) }
                )
            }
        }
    }
}

@Composable
private fun TarjetaInstrumento(
    instrumento: Instrumento,
    onAplicar: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8FAFC)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Surface(
                color = Color(0xFFE0F2FE),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = instrumento.nivel,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulPrimario
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = instrumento.titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextoNegro
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = instrumento.descripcion,
                fontSize = 14.sp,
                color = TextoGrisOscuro
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "⏱ ${instrumento.tiempoEstimado}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextoGrisOscuro
                    )
                    Text(
                        text = "📝 ${instrumento.preguntas} preguntas",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextoGrisOscuro
                    )
                }

                Button(
                    onClick = onAplicar,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulPrimario,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Aplicar", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CatalogoScreenPreview() {
    CatalogoScreen()
}

