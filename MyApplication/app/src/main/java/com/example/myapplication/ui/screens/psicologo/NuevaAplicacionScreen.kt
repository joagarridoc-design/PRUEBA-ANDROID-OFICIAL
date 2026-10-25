package com.example.myapplication.ui.screens.psicologo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.BottomBarPsicologo
import com.example.myapplication.ui.theme.AzulPrimario
import com.example.myapplication.ui.theme.BordeInput
import com.example.myapplication.ui.theme.FondoBlanco
import com.example.myapplication.ui.theme.TextoGrisOscuro
import com.example.myapplication.ui.theme.TextoNegro
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaAplicacionScreen(
    onNavegar: (String) -> Unit = {},
    onVolverClick: () -> Unit = {}
) {
    val clipboardManager = LocalClipboardManager.current

    var nombreEstudiante by remember { mutableStateOf("") }
    var cursoEstudiante by remember { mutableStateOf("") }
    var instrumentoSeleccionado by remember { mutableStateOf("Cuestionario de Bienestar Escolar") }
    var codigoGenerado by remember { mutableStateOf<String?>(null) }
    var codigoCopiado by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nueva Aplicación",
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
                rutaActual = "nueva_aplicacion",
                onNavegar = onNavegar
            )
        },
        containerColor = FondoBlanco
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Asignar evaluación a un estudiante",
                    fontSize = 14.sp,
                    color = TextoGrisOscuro
                )

                // Nombre del estudiante
                OutlinedTextField(
                    value = nombreEstudiante,
                    onValueChange = { nombreEstudiante = it },
                    label = { Text("Nombre o Iniciales del estudiante") },
                    placeholder = { Text("Ej: Mateo Silva") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AzulPrimario,
                        unfocusedBorderColor = BordeInput,
                        focusedLabelColor = AzulPrimario,
                        focusedTextColor = TextoNegro,
                        unfocusedTextColor = TextoNegro
                    )
                )

                // Curso
                OutlinedTextField(
                    value = cursoEstudiante,
                    onValueChange = { cursoEstudiante = it },
                    label = { Text("Curso / Nivel") },
                    placeholder = { Text("Ej: 4° Básico A") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AzulPrimario,
                        unfocusedBorderColor = BordeInput,
                        focusedLabelColor = AzulPrimario,
                        focusedTextColor = TextoNegro,
                        unfocusedTextColor = TextoNegro
                    )
                )

                // Selección de Instrumento
                OutlinedTextField(
                    value = instrumentoSeleccionado,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text("Instrumento a aplicar") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = AzulPrimario,
                        unfocusedBorderColor = BordeInput,
                        focusedLabelColor = AzulPrimario,
                        focusedTextColor = TextoNegro,
                        unfocusedTextColor = TextoNegro
                    )
                )

                // Cuadro del Código Generado con función de copia
                if (codigoGenerado != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F9FF)),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(2.dp, AzulPrimario)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "¡CÓDIGO GENERADO!",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = AzulPrimario,
                                letterSpacing = 1.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = codigoGenerado!!,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = TextoNegro,
                                letterSpacing = 4.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Botón de Copiado al Portapapeles
                            OutlinedButton(
                                onClick = {
                                    clipboardManager.setText(AnnotatedString(codigoGenerado!!))
                                    codigoCopiado = true
                                },
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = if (codigoCopiado) Color(0xFF16A34A) else AzulPrimario
                                ),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (codigoCopiado) Color(0xFF16A34A) else AzulPrimario
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                if (codigoCopiado) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Copiado",
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = "¡Código copiado!", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                } else {
                                    Text(text = "📋 Copiar código", fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "Entrega este código al estudiante para que inicie su sesión.",
                                fontSize = 13.sp,
                                color = TextoGrisOscuro,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            // Botón Generar/Regenerar Código
            Button(
                onClick = {
                    val num = Random.nextInt(100000, 999999)
                    codigoGenerado = num.toString()
                    codigoCopiado = false
                },
                enabled = nombreEstudiante.isNotBlank() && cursoEstudiante.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrimario,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = if (codigoGenerado == null) "Generar Código de Acceso" else "Regenerar Código",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NuevaAplicacionScreenPreview() {
    NuevaAplicacionScreen()
}

