package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.*

@Composable
fun LoginScreen(
    onLoginExitoso: () -> Unit = {},
    onVolverEstudiante: () -> Unit = {}
) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

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
            Text(
                text = "Acceso Profesional PIE",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = AzulPrimario
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo Usuario
            OutlinedTextField(
                value = usuario,
                onValueChange = { usuario = it },
                label = { Text("Usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextoNegro,          // Texto en NEGRO
                    unfocusedTextColor = TextoNegro,        // Texto en NEGRO
                    focusedBorderColor = AzulPrimario,
                    unfocusedBorderColor = BordeInput,
                    focusedLabelColor = AzulPrimario,
                    unfocusedLabelColor = TextoGrisOscuro
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Contraseña
            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = TextoNegro,          // Texto en NEGRO
                    unfocusedTextColor = TextoNegro,        // Texto en NEGRO
                    focusedBorderColor = AzulPrimario,
                    unfocusedBorderColor = BordeInput,
                    focusedLabelColor = AzulPrimario,
                    unfocusedLabelColor = TextoGrisOscuro
                )
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Botón Ingresar
            Button(
                onClick = onLoginExitoso,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrimario,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Ingresar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onVolverEstudiante) {
                Text(
                    text = "Ingresar como estudiante",
                    color = AzulSecundario,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

