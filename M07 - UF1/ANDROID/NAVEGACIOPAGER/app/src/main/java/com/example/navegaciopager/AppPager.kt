package com.example.navegaciopager

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navegaciopager.navegacio.GrafNavegacio
import com.example.navegaciopager.navegacio.categoriesDeNavegacio

@Preview
@SuppressLint("RestrictedApi")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppPager()
{
    val controladorDeNavegacio = rememberNavController();
    val rutaActual by controladorDeNavegacio.currentBackStackEntryAsState()
    val destinacioActual = rutaActual?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                categoriesDeNavegacio.forEach{categoria ->
                    NavigationBarItem(
                        selected = destinacioActual?.hierarchy?.any{it.hasRoute(categoria.ruta::class)} == true,
                        onClick = {
                            controladorDeNavegacio.navigate(categoria.ruta){
                                popUpTo(controladorDeNavegacio.graph.findStartDestination().id){
                                    inclusive = false
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            if(destinacioActual?.hierarchy?.any{it.hasRoute(categoria.ruta::class)} == true){
                                Icon(
                                    imageVector = categoria.iconaSeleccionada,
                                    contentDescription = categoria.titol
                                )
                            }
                            else{
                                Icon(
                                    imageVector = categoria.iconaNoSeleccionada,
                                    contentDescription = categoria.titol
                                )
                            }
                        },
                        label = {Text(text = categoria.titol)}
                    )
                }

            }
        }

        ) { innerPadding ->
        GrafNavegacio(
            navController = controladorDeNavegacio,
            paddingValues = innerPadding
        )
    }
}