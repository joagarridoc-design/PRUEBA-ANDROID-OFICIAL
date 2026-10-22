package com.example.myapplication.ui.screens.psicologo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PanelPrincipalScreen(
    onNavegar: (String) -> Unit = {},
    onCerrarSesion: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Panel principal",
                        fontWeight = FontWeight.Bold,
                        color = TextoNegro,
                        fontSize = 20.sp
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                            .size(38.dp)
                            .background(AzulPrimario, shape = RoundedCornerShape(10.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "MP",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onCerrarSesion) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = TextoNegro
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoBlanco)
            )
        },
        bottomBar = {
            BottomBarPsicologo(
                rutaActual = "panel_principal",
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
                Spacer(modifier = Modifier.height(12.dp))

                // Saludo y Avatar de perfil
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Buenos días",
                            fontSize = 14.sp,
                            color = TextoGrisOscuro
                        )
                        Text(
                            text = "Profesional PIE",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextoNegro
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0xFFE0F2FE), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            tint = AzulPrimario,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Aplicaciones",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextoNegro
                )
            }

            // Tarjeta 1: Pendiente
            item {
                TarjetaResumenEstado(
                    tituloEstado = "Pendiente",
                    colorBadge = Color(0xFFFEF08A),
                    colorTextoBadge = Color(0xFF854D0E),
                    cantidad = "4",
                    onDetallesClick = { onNavegar("lista_aplicaciones") }
                )
            }

            // Tarjeta 2: En desarrollo
            item {
                TarjetaResumenEstado(
                    tituloEstado = "En desarrollo",
                    colorBadge = Color(0xFFE0F2FE),
                    colorTextoBadge = Color(0xFF0369A1),
                    cantidad = "2",
                    onDetallesClick = { onNavegar("lista_aplicaciones") }
                )
            }

            // Tarjeta 3: Finalizada
            item {
                TarjetaResumenEstado(
                    tituloEstado = "Finalizada",
                    colorBadge = Color(0xFFDCFCE7),
                    colorTextoBadge = Color(0xFF15803D),
                    cantidad = "12",
                    onDetallesClick = { onNavegar("lista_aplicaciones") }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun TarjetaResumenEstado(
    tituloEstado: String,
    colorBadge: Color,
    colorTextoBadge: Color,
    cantidad: String,
    onDetallesClick: () -> Unit
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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = colorBadge,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        text = tituloEstado,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorTextoBadge
                    )
                }

                TextButton(onClick = onDetallesClick) {
                    Text(
                        text = "Detalles",
                        color = AzulPrimario,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = cantidad,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = TextoNegro
            )

            Text(
                text = "aplicaciones",
                fontSize = 14.sp,
                color = TextoGrisOscuro
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PanelPrincipalScreenPreview() {
    PanelPrincipalScreen()
}

