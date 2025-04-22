package com.montilivi.projecteautentificacio.navegacio

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.montilivi.llistacompra.navegacio.PantallaCategories
import com.montilivi.llistacompra.navegacio.PantallaIniciSessio
import com.montilivi.llistacompra.navegacio.PantallaLlistaCompra
import com.montilivi.llistacompra.navegacio.PantallaPerfil
import com.montilivi.llistacompra.navegacio.PantallaPortada
import com.montilivi.llistacompra.navegacio.PantallaRegitrar
import com.montilivi.projecteautentificacio.analitiques.analituquesManager
import com.montilivi.projecteautentificacio.autentificacio.autentificationManager
import com.montilivi.projecteautentificacio.ui.pantalles.Categoria
import com.montilivi.projecteautentificacio.ui.pantalles.IniciSessio
import com.montilivi.projecteautentificacio.ui.pantalles.LlistaCompra
import com.montilivi.projecteautentificacio.ui.pantalles.Perfil
import com.montilivi.projecteautentificacio.ui.pantalles.Portada
import com.montilivi.projecteautentificacio.ui.pantalles.Registrar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GrafNavegacio(
    controlNavegacio: NavHostController,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    menegadorAnalitiques: analituquesManager,
    manegadorAutentificacio: autentificationManager
){
    val startDestination :() -> Unit = { if (manegadorAutentificacio.current_user_exists()){ controlNavegacio.navigate(PantallaPortada)} else {controlNavegacio.navigate(PantallaIniciSessio)}}

    NavHost(
        controlNavegacio,
        startDestination = if (manegadorAutentificacio.current_user_exists()){ PantallaPortada} else {PantallaIniciSessio},
        modifier = Modifier.padding(paddingValues)
    ){
        composable<PantallaIniciSessio>{
            IniciSessio(
                onClickRegistrar={
                    controlNavegacio.navigate(PantallaRegitrar)
                },
                manegadorAutentificacio,
                menegadorAnalitiques,
                startDestination
            )
        }
        composable<PantallaRegitrar>{
            Registrar(
                manegadorAutentificacio,
                menegadorAnalitiques,
                onRegistrar={
                    controlNavegacio.navigate(PantallaPortada)
                }
            )
        }
        composable<PantallaPortada>{
            Portada(menegadorAnalitiques)
        }
        composable<PantallaPerfil>{
            Perfil(
                menegadorAnalitiques,
                manegadorAutentificacio,
                startDestination
            )
        }
        composable<PantallaCategories>{
            Categoria()
        }
        composable<PantallaLlistaCompra>{
            LlistaCompra(autentificationManager = manegadorAutentificacio, analituquesManager = menegadorAnalitiques)
        }
    }
}