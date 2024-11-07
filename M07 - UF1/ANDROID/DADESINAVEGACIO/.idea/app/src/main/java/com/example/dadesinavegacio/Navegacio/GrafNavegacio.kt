package com.example.dadesinavegacio.Navegacio

import androidx.compose.foundation.layout.Arrangement
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
import androidx.navigation.toRoute
import com.example.dadesinavegacio.ui.Componibles.ComponibleHoritzontalA
import com.example.dadesinavegacio.ui.Componibles.ComponibleHoritzontalC
import com.example.dadesinavegacio.ui.Componibles.ComponibleHoritzontalD
import com.example.dadesinavegacio.ui.Componibles.ComponibleVerticalA
import com.example.dadesinavegacio.ui.Componibles.ComponibleVerticalC
import com.example.dadesinavegacio.ui.Componibles.ComponibleVerticalD
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
        startDestination = CategoriaArtistes,
        modifier = Modifier.padding(paddingValues)
    ){
        CategoriaArtistes(navController)
        CategoriaDiscos(navController)
        CategoriaConcerts(navController)
    }
}

fun NavGraphBuilder.CategoriaArtistes(navController: NavHostController){
    navigation<CategoriaArtistes>(startDestination = MenuArtiste) {
        composable<MenuArtiste>{
            PantallaArtista(
                onLlistaVAClick = {
                    navController.navigate(LlistaVA){popUpTo(MenuArtiste){inclusive = false} }
                },
                onLlistaHAClick = {
                    navController.navigate(LlistaHA){popUpTo(MenuArtiste){inclusive = false} }
                }
            )
        }
        composable<LlistaVA>{
            LlistaVerticalA(
                onClickElement = {id ->
                    navController.navigate(PantallaDA(id)){
                        popUpTo(MenuArtiste)
                    }
                }
            )
        }
        composable<LlistaHA>{
            LlistaHoritzontalA(
                onClickElement = {id ->
                    navController.navigate(PantallaDA(id)){
                        popUpTo(MenuArtiste)
                    }

                }
            )
        }
        composable<PantallaDA>{
            val id = it.toRoute<PantallaDA>()
            PantallaDetalladaA(id.dada)
        }
    }
}

fun NavGraphBuilder.CategoriaConcerts(navController: NavHostController){
    navigation<CategoriaConcerts>(startDestination = MenuConsert) {
        composable<MenuConsert>{
            PantallaConcerts(
                onLlistaVCClick =  {
                    navController.navigate(LlistaVC){popUpTo(MenuDisc) {inclusive = false}}
                },
                onLlistaHCClick = {
                    navController.navigate(LlistaHC){popUpTo(MenuDisc) {inclusive = false}}
                }
            )
        }
        composable<LlistaVC>{
            LlistaVerticalC(
                onClickElement = {id ->
                    navController.navigate(PantallaDC(id)){
                        popUpTo(MenuConsert)
                    }
                }
            )
        }
        composable<LlistaHC>{
            LlistaHoritzontalC(
                onClickElement = {id ->
                    navController.navigate(PantallaDC(id)){
                        popUpTo(MenuConsert)
                    }
                }
            )
        }
        composable<PantallaDC>{
            val id = it.toRoute<PantallaDC>()
            PantallaDetalladaC(id.dada)
        }
    }
}
fun NavGraphBuilder.CategoriaDiscos(navController: NavHostController){
    navigation<CategoriaDiscos>(startDestination = MenuDisc) {
        composable<MenuDisc>{
            PantallaDisco(
                onLlistaVDClick = {
                    navController.navigate(LlistaVD){popUpTo(MenuArtiste){inclusive = false} }
                },
                onLlistaHDClick = {
                    navController.navigate(LlistaHD){popUpTo(MenuArtiste){inclusive = false} }
                }
            )
        }
        composable<LlistaVD>{
            LlistaVerticalD(
                onClickElement = {id ->
                    navController.navigate(PantallaDD(id)){
                        popUpTo(MenuDisc)
                    }
                }
            )
        }
        composable<LlistaHD>{
            LlistaHoritzontalD(
                onClickElement = {id ->
                    navController.navigate(PantallaDD(id)){
                        popUpTo(MenuDisc)
                    }
                }
            )
        }
        composable<PantallaDD>{
            val id = it.toRoute<PantallaDD>()
            PantallaDetalladaD(id.dada)
        }
    }
}
