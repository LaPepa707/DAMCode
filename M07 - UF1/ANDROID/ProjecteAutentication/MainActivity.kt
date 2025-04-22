package com.montilivi.projecteautentificacio

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.common.base.Objects
import com.montilivi.llistacompra.navegacio.PantallaCategories
import com.montilivi.llistacompra.navegacio.PantallaLlistaCompra
import com.montilivi.llistacompra.navegacio.PantallaPerfil
import com.montilivi.llistacompra.navegacio.PantallaPortada
import com.montilivi.llistacompra.navegacio.categoriaNavegacio
import com.montilivi.projecteautentificacio.analitiques.analituquesManager
import com.montilivi.projecteautentificacio.autentificacio.autentificationManager
import com.montilivi.projecteautentificacio.navegacio.GrafNavegacio
import com.montilivi.projecteautentificacio.ui.theme.ProjecteAutentificacioTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjecteAutentificacioTheme {
                Aplicacio()
            }
        }
    }
}

@Composable
fun Aplicacio()
{
    val navController = rememberNavController()

    val ambitCorrutina = rememberCoroutineScope()

    val estatDrawer = rememberDrawerState(DrawerValue.Closed)

    val rutaActual by navController.currentBackStackEntryAsState()

    val destinacioActual = rutaActual?.destination

    val manegadorAnalitiques = analituquesManager(LocalContext.current)
    val manegadorAutenticacio = autentificationManager(LocalContext.current)

    CalaixDeNavegacio(navController, estatDrawer, ambitCorrutina, destinacioActual, rutaActual, manegadorAutenticacio, manegadorAnalitiques)
}

@Composable
fun CalaixDeNavegacio(
    navController: NavHostController,
    estatDrawer: DrawerState,
    ambitCorrutina: CoroutineScope,
    destinacioActual: NavDestination?,
    rutaActual: NavBackStackEntry?,
    manegadorAutenticacio: autentificationManager,
    manegadorAnalitiques: analituquesManager
)
{
    ModalNavigationDrawer(
        drawerState = estatDrawer,
        drawerContent = {
            ModalDrawerSheet(
                drawerContentColor = Color(209,131,169),
                drawerContainerColor = Color(75, 21, 53),
                windowInsets = WindowInsets(left = 24.dp, right = 24.dp, top = 48.dp)
            )
            {
                Icon(painter = painterResource(id = R.drawable.firebase),
                    contentDescription = "Icono de la aplicación",
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))

                HorizontalDivider(
                    modifier = Modifier.height(15.dp)
                )

                categoriaNavegacio.forEach{ destinacio->
                    NavigationDrawerItem(
                        label = { Text(destinacio.titol) },
                        selected = destinacioActual?.hierarchy?.any {it.hasRoute(destinacio.ruta::class) } == true,
                        icon = {
                            Icon(
                                imageVector = if (destinacioActual?.hierarchy?.any {it.hasRoute(destinacio.ruta::class) } == true)
                                    destinacio.iconaSeleccionada else destinacio.iconaNoSeleccionada,
                                contentDescription = destinacio.titol
                            )
                        },
                        colors = NavigationDrawerItemDefaults.colors(
                            unselectedTextColor = Color(75, 21, 53),
                            unselectedIconColor = Color(75, 21, 53),
                            selectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            selectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            selectedContainerColor = MaterialTheme.colorScheme.primary,
                            unselectedContainerColor = Color(209,131,169),
                            selectedBadgeColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedBadgeColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        shape = ShapeDefaults.Medium,
                        onClick = {
                            ambitCorrutina.launch {
                                estatDrawer.close()
                            }

                            navController.navigate(destinacio.ruta) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        gesturesEnabled = esPotObrirElDrawer(destinacioActual)
    )
    {
        ApplicationScaffold(
            rutaActual,
            destinacioActual,
            navController,
            estatDrawer,
            ambitCorrutina,
            manegadorAnalitiques,
            manegadorAutenticacio
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationScaffold(
    rutaActual: NavBackStackEntry?,
    destinacioActual: NavDestination?,
    navController: NavHostController,
    estatDrawer: DrawerState,
    ambitCorrutina: CoroutineScope,
    manegadorAnalitiques: analituquesManager,
    manegadorAutenticacio: autentificationManager
) {
    Scaffold(

        topBar ={
            if(esPotObrirElDrawer(destinacioActual)){
                TopAppBar(title = { Text("") },
                    navigationIcon = {
                        if (listOf(
                                PantallaPortada::class
                            ).any{ destinacioActual?.hasRoute(it) == true }
                        ) {  // <-- Cal actualitzar aquesta condició a la ruta de la pantalla principal
                            IconButton(
                                onClick = {
                                    ambitCorrutina.launch{
                                        estatDrawer.open()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Pantalla principal",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { navController.navigateUp() }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Navega enrera",
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(75, 21, 53),
                        titleContentColor = Color(243, 200, 221),
                        navigationIconContentColor = Color(243, 200, 221)
                    )
                )
            }

        }
    ) { paddingValues ->
        GrafNavegacio(navController, paddingValues, manegadorAnalitiques, manegadorAutenticacio)
    }
}

fun esPotObrirElDrawer(destinacioActual: NavDestination?): Boolean =
    listOf(
        PantallaPortada::class,
        PantallaPerfil::class,
        PantallaCategories::class,
        PantallaLlistaCompra::class
    ).any{destinacioActual?.hasRoute(it)?:true}