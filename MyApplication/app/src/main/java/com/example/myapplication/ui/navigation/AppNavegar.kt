package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screens.LoginScreen
import com.example.myapplication.ui.screens.estudiante.IngresoCodigoScreen
import com.example.myapplication.ui.screens.estudiante.InstruccionesScreen
import com.example.myapplication.ui.screens.estudiante.ResponderScreen
import com.example.myapplication.ui.screens.psicologo.CatalogoScreen
import com.example.myapplication.ui.screens.psicologo.HistorialScreen
import com.example.myapplication.ui.screens.psicologo.ListaAplicacionesScreen
import com.example.myapplication.ui.screens.psicologo.NuevaAplicacionScreen
import com.example.myapplication.ui.screens.psicologo.PanelPrincipalScreen
import com.example.myapplication.ui.screens.psicologo.RevisionScreen
import com.example.myapplication.ui.screens.psicologo.SupervisionScreen

@Composable
fun AppNavegar() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Ruta.IngresoCodigo.route
    ) {
        // 1. Ingreso con Código
        composable(Ruta.IngresoCodigo.route) {
            IngresoCodigoScreen(
                onIngresarClick = { _ ->
                    navController.navigate(Ruta.Instrucciones.route)
                },
                onIrALoginProfesional = {
                    navController.navigate(Ruta.Login.route)
                }
            )
        }

        // 2. Instrucciones
        composable(Ruta.Instrucciones.route) {
            InstruccionesScreen(
                onComenzarClick = {
                    navController.navigate(Ruta.Responder.route)
                }
            )
        }

        // 3. Responder Cuestionario
        composable(Ruta.Responder.route) {
            ResponderScreen(
                onFinalizarClick = {
                    navController.navigate(Ruta.Confirmacion.route)
                },
                onVolverAInstrucciones = {
                    navController.popBackStack()
                }
            )
        }

        // 4. Confirmación Estudiante
        composable(Ruta.Login.route) {
            LoginScreen(
                onLoginExitoso = {
                    navController.navigate(Ruta.PanelPrincipal.route) {
                        popUpTo(Ruta.IngresoCodigo.route) { inclusive = true }
                    }
                },
                onVolverEstudiante = {
                    navController.popBackStack()
                }
            )
        }

        // 6. Panel Principal Psicólogo
        composable(Ruta.PanelPrincipal.route) {
            PanelPrincipalScreen(
                onNavegar = { ruta ->
                    navController.navigate(ruta)
                },
                onCerrarSesion = {
                    navController.navigate(Ruta.IngresoCodigo.route) {
                        // Borra todo el historial de pantallas para obligar a autenticarse de nuevo
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        // 7. Catálogo Psicólogo
        composable(Ruta.Catalogo.route) {
            CatalogoScreen(
                onNavegar = { ruta ->
                    navController.navigate(ruta)
                },
                onAplicarInstrumento = { _ ->
                    navController.navigate(Ruta.NuevaAplicacion.route)
                }
            )
        }

        // 8. Nueva Aplicación Psicólogo
        composable(Ruta.NuevaAplicacion.route) {
            NuevaAplicacionScreen(
                onNavegar = { ruta ->
                    navController.navigate(ruta)
                },
                onVolverClick = {
                    navController.popBackStack()
                }
            )
        }
        // 9. Lista de Aplicaciones Psicólogo
        composable(Ruta.ListaAplicaciones.route) {
            ListaAplicacionesScreen(
                onNavegar = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }
        // 10. Historial Psicólogo
        composable(Ruta.Historial.route) {
            HistorialScreen(
                onNavegar = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        // 11. Revisión de Evaluación
        composable(Ruta.Revision.route) {
            RevisionScreen(
                onNavegar = { ruta -> navController.navigate(ruta) },
                onVolverClick = { navController.popBackStack() }
            )
        }

       // 12. Supervisión en Vivo
        composable(Ruta.Supervision.route) {
            SupervisionScreen(
                onNavegar = { ruta -> navController.navigate(ruta) },
                onVolverClick = { navController.popBackStack() }
            )
        }


    }

}


