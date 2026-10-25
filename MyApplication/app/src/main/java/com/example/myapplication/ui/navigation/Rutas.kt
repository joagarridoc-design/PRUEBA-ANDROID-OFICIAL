package com.example.myapplication.ui.navigation

sealed class Ruta(val route: String) {

    // Estudiante
    object IngresoCodigo : Ruta("ingreso_codigo")
    object Instrucciones : Ruta("instrucciones")
    object Responder : Ruta("responder")
    object Confirmacion : Ruta("confirmacion")

    // Psicólogo
    object Login : Ruta("login")
    object PanelPrincipal : Ruta("panel_principal")
    object Catalogo : Ruta("catalogo")
    object NuevaAplicacion : Ruta("nueva_aplicacion")
    object ListaAplicaciones : Ruta("lista_aplicaciones")
    object Historial : Ruta("historial")
    object Revision : Ruta("revision")
    object Supervision : Ruta("supervision")
}