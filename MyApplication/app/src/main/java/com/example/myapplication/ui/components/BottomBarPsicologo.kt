package com.example.myapplication.ui.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.AzulPrimario
import com.example.myapplication.ui.theme.FondoBlanco
import com.example.myapplication.ui.theme.TextoNegro

@Composable
fun BottomBarPsicologo(
    rutaActual: String,
    onNavegar: (String) -> Unit
) {
    val coloresItem = NavigationBarItemDefaults.colors(
        selectedIconColor = Color.White,
        selectedTextColor = AzulPrimario,
        indicatorColor = AzulPrimario,
        unselectedIconColor = Color(0xFF334155),
        unselectedTextColor = TextoNegro
    )

    NavigationBar(
        containerColor = FondoBlanco,
        contentColor = AzulPrimario,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            selected = rutaActual == "catalogo",
            onClick = { onNavegar("catalogo") },
            icon = { Icon(Icons.Default.Menu, contentDescription = "Catálogo") },
            label = {
                Text(
                    text = "Catálogo",
                    fontSize = 11.sp,
                    fontWeight = if (rutaActual == "catalogo") FontWeight.Bold else FontWeight.Medium
                )
            },
            colors = coloresItem
        )

        NavigationBarItem(
            selected = rutaActual == "panel_principal",
            onClick = { onNavegar("panel_principal") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = {
                Text(
                    text = "Inicio",
                    fontSize = 11.sp,
                    fontWeight = if (rutaActual == "panel_principal") FontWeight.Bold else FontWeight.Medium
                )
            },
            colors = coloresItem
        )

        FloatingActionButton(
            onClick = { onNavegar("nueva_aplicacion") },
            shape = CircleShape,
            containerColor = AzulPrimario,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Nueva Aplicación")
        }

        NavigationBarItem(
            selected = rutaActual == "lista_aplicaciones",
            onClick = { onNavegar("lista_aplicaciones") },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Aplicaciones") },
            label = {
                Text(
                    text = "Aplicaciones",
                    fontSize = 11.sp,
                    fontWeight = if (rutaActual == "lista_aplicaciones") FontWeight.Bold else FontWeight.Medium
                )
            },
            colors = coloresItem
        )

        NavigationBarItem(
            selected = rutaActual == "historial",
            onClick = { onNavegar("historial") },
            icon = { Icon(Icons.Default.DateRange, contentDescription = "Historial") },
            label = {
                Text(
                    text = "Historial",
                    fontSize = 11.sp,
                    fontWeight = if (rutaActual == "historial") FontWeight.Bold else FontWeight.Medium
                )
            },
            colors = coloresItem
        )
    }
}