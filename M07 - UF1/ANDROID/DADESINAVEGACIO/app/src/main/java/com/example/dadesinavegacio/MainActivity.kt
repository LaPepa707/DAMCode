package com.example.dadesinavegacio

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dadesinavegacio.Navegacio.GrafNavegacio
import com.example.dadesinavegacio.Navegacio.Principal
import com.example.dadesinavegacio.Navegacio.categoriesDeNavegacio
import com.example.dadesinavegacio.ui.theme.DadesINavegacioTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DadesINavegacioTheme {
                App()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RestrictedApi")
@Preview ()

@Composable
fun App()
{
    val navController= rememberNavController()
    val rutaActual by navController.currentBackStackEntryAsState()
    val destinacioActual=rutaActual?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {Text("Navegació") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.inversePrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.inversePrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.inversePrimary
                ),
                navigationIcon = {
                    if(destinacioActual?.hasRoute(Principal::class)?:true)
                        IconButton(onClick = {})
                        {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Home"
                            )
                        }
                    else{
                        IconButton(onClick = {navController.navigateUp()})
                        {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                categoriesDeNavegacio.forEach { categoria ->
                    NavigationBarItem(
                        selected = destinacioActual?.hierarchy?.any{it.hasRoute(categoria.ruta::class)} == true,
                        onClick = {
                            navController.navigate(categoria.ruta){
                                popUpTo(navController.graph.startDestinationId){
                                    inclusive = true
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
    )
    {
            paddingValues ->
        GrafNavegacio(navController, paddingValues)
    }
}

@Composable
fun TemaDeLaAplicacio(content: @Composable ()->Unit)
{
    DadesINavegacioTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}