package com.example.myapplication.ui.screens.estudiante

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.*

@Composable
fun IngresoCodigoScreen(
    onIngresarClick: (String) -> Unit = {},
    onIrALoginProfesional: () -> Unit = {}
) {
    var codigo by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = FondoBlanco
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título
            Text(
                text = "MPAZ PsicoEvalúa",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = AzulPrimario
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtítulo / Instrucciones
            Text(
                text = "Escribe el código que te entregó la persona que te acompaña.",
                fontSize = 15.sp,
                color = TextoGrisOscuro,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Formulario con texto e input NEGRO
            OutlinedTextField(
                value = codigo,
                onValueChange = { codigo = it },
                label = { Text("Código temporal") },
                placeholder = { Text("Ej: A7K-92X", color = Color.Gray) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextoNegro,          // Texto que escribe el usuario en NEGRO
                    unfocusedTextColor = TextoNegro,        // Texto que escribe el usuario en NEGRO
                    focusedBorderColor = AzulPrimario,
                    unfocusedBorderColor = BordeInput,
                    focusedLabelColor = AzulPrimario,
                    unfocusedLabelColor = TextoGrisOscuro
                )
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Botón Principal
            Button(
                onClick = { onIngresarClick(codigo) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = codigo.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrimario,
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFFCBD5E1),
                    disabledContentColor = Color(0xFF64748B)
                )
            ) {
                Text(
                    text = "Entrar a mi actividad",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Enlace Secundario
            TextButton(onClick = onIrALoginProfesional) {
                Text(
                    text = "Acceso profesional PIE",
                    color = AzulSecundario,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}