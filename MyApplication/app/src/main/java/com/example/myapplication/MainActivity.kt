package com.example.myapplication

import android.app.Service
import android.os.Bundle

import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.*

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

import com.example.myapplication.data.local.AppDatabase

import com.example.myapplication.data.local.PsicologoEntity


import com.example.myapplication.Service.RetrofitClient

import com.example.myapplication.model.PsicologoModel
import com.google.firebase.appdistribution.gradle.ApiService

import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)



        // Inicializar Room localmente

        val database = AppDatabase.obtenerBaseDeDatos(applicationContext)

        val dao = database.psicologoDao()



        setContent {

            Surface(

                modifier = Modifier.fillMaxSize(),

                color = MaterialTheme.colorScheme.background

            ) {

                FormularioPsicologoScreen(dao = dao)

            }

        }

    }

}



@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun FormularioPsicologoScreen(dao: com.example.myapplication.data.local.PsicologoDao) {

    var username by remember { mutableStateOf("") }

    var passwordHash by remember { mutableStateOf("") }

    var mensajeEstado by remember { mutableStateOf("") }




    val scope = rememberCoroutineScope()



    Scaffold(

        topBar = {

            TopAppBar(title = { Text("Registro de Psicologos (Dual)") })

        }

    ) { innerPadding ->

        Column(

            modifier = Modifier

                .padding(innerPadding)

                .padding(16.dp)

                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            OutlinedTextField(

                value = username,

                onValueChange = { username = it },

                label = { Text("Nombre del Psicologo") },

                modifier = Modifier.fillMaxWidth()

            )



            OutlinedTextField(

                value = passwordHash,

                onValueChange = { passwordHash= it },

                label = { Text("Contraseña") },

                modifier = Modifier.fillMaxWidth()

            )







            Spacer(modifier = Modifier.height(8.dp))



            // Botón 1: Guardar localmente (SQLite / Room)

            Button(

                onClick = {

                    if (username.isNotBlank() && passwordHash.isNotBlank()) {

                        scope.launch(Dispatchers.IO) {

                            dao.insertarPsicologo(

                                PsicologoEntity(

                                    username = username,

                                    passwordHash = passwordHash



                                )

                            )

                        }

                        mensajeEstado = "¡Guardado localmente (SQLite) con éxito!"

                    } else {

                        mensajeEstado = "Completa los campos obligatorios"

                    }

                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Guardar Local (SQLite)")

            }



            // Botón 2: Guardar en la nube (MySQL mediante Node.js)

            Button(

                onClick = {

                    if (username.isNotBlank() && passwordHash.isNotBlank()) {

                        scope.launch(Dispatchers.IO) {

                            try {

                                val nuevoPsicologo = PsicologoModel(

                                    username = username,

                                    passwordHash = passwordHash



                                )

                                val response = RetrofitClient.api.guardarPsicologo(nuevoPsicologo)

                                // Nota: ajusta a ApiService.RetrofitClient según hayas nombrado tu objeto en la sección anterior

                            } catch (e: Exception) {

                                // Manejar error de red o servidor apagado

                            }

                        }

                        mensajeEstado = "Enviando a MySQL (Node.js)... Revisa servidor"

                    } else {

                        mensajeEstado = "Completa los campos obligatorios"

                    }

                },

                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Guardar Remoto (MySQL - 3306)")

            }



            if (mensajeEstado.isNotBlank()) {

                Text(

                    text = mensajeEstado,

                    color = MaterialTheme.colorScheme.primary,

                    modifier = Modifier.padding(top = 8.dp)

                )

            }

        }

    }

}