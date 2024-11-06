package com.example.dadesinavegacio.Navegacio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dadesinavegacio.Model.Disco
import com.example.dadesinavegacio.ui.Llista.LlistaHoritzontalA
import com.example.dadesinavegacio.ui.Llista.LlistaHoritzontalC
import com.example.dadesinavegacio.ui.Llista.LlistaHoritzontalD
import com.example.dadesinavegacio.ui.Llista.LlistaVerticalA
import com.example.dadesinavegacio.ui.Llista.LlistaVerticalC
import com.example.dadesinavegacio.ui.Llista.LlistaVerticalD
import com.example.dadesinavegacio.ui.Pantalles.PantallaArtista
import com.example.dadesinavegacio.ui.Pantalles.PantallaConcerts
import com.example.dadesinavegacio.ui.Pantalles.PantallaDetalladaA
import com.example.dadesinavegacio.ui.Pantalles.PantallaDetalladaC
import com.example.dadesinavegacio.ui.Pantalles.PantallaDetalladaD
import com.example.dadesinavegacio.ui.Pantalles.PantallaDisco
import com.example.dadesinavegacio.ui.Pantalles.PantallaPrincipal

@Composable
fun GrafNavegacio(
    navController: NavHostController,
    paddingValues: PaddingValues = PaddingValues(0.dp)
){
    NavHost(
        navController = navController,
        startDestination = Principal,
        modifier = Modifier.padding(paddingValues)
    ){
        composable<Principal>{
            PantallaPrincipal(
                onArtistesClick = {
                    navController.navigate(Artistes)
                },
                onConcertsClick = {
                    navController.navigate(Concerts)
                },
                onDiscosClick = {
                    navController.navigate(Discos)
                }
            )
        }
        composable<Artistes>{
            PantallaArtista(
                onLlistaVAClick = {
                    navController.navigate(LlistaVA){popUpTo(Artistes){inclusive = false} }
                },
                onLlistaHAClick = {
                    navController.navigate(LlistaHA){popUpTo(Artistes){inclusive = false} }
                },
                onPantallaDAClick = {
                    navController.navigate(PantallaDA){popUpTo(Artistes){inclusive = false} }
                }
            )
        }
        composable<LlistaVA>{
            LlistaVerticalA()
        }
        composable<LlistaHA>{
            LlistaHoritzontalA()
        }
        composable<PantallaDA>{
            PantallaDetalladaA()
        }
        composable<Concerts>{
            PantallaConcerts(
                onLlistaVCClick = {
                    navController.navigate(LlistaVC){popUpTo(Concerts){inclusive = false} }
                },
                onLlistaHCClick = {
                    navController.navigate(LlistaHC){popUpTo(Concerts){inclusive = false} }
                },
                onPantallaDCClick = {
                    navController.navigate(PantallaDC){popUpTo(Concerts){inclusive = false} }
                }
            )
        }
        composable<LlistaVC>{
            LlistaVerticalC()
        }
        composable<LlistaHC>{
            LlistaHoritzontalC()
        }
        composable<PantallaDC>{
            PantallaDetalladaC()
        }
        composable<Discos> {
            PantallaDisco(
                onLlistaVDClick = {
                    navController.navigate(LlistaVD){popUpTo(Discos){inclusive = false} }
                },
                onLlistaHDClick = {
                    navController.navigate(LlistaHD){popUpTo(Discos){inclusive = false} }
                },
                onPantallaDDClick = {
                    navController.navigate(PantallaDD){popUpTo(Discos){inclusive = false} }
                }
            )
        }
        composable<LlistaVD>{
            LlistaVerticalD()
        }
        composable<LlistaHD>{
            LlistaHoritzontalD()
        }
        composable<PantallaDD>{
            PantallaDetalladaD()
        }
    }
}
