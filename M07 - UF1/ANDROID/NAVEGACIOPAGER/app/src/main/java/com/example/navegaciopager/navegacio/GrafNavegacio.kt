package com.example.navegaciopager.navegacio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.navegaciopager.dades.Artistes.obtenDadesDelsArtistes
import com.example.navegaciopager.dades.Concerts.obtenDadesDelsConcerts
import com.example.navegaciopager.dades.Discos.obtenDadesDelsDiscos
import com.example.navegaciopager.ui.llista.LlistaA
import com.example.navegaciopager.ui.llista.LlistaC
import com.example.navegaciopager.ui.llista.LlistaD

@Composable
fun GrafNavegacio(
    navController : NavHostController,
    paddingValues: PaddingValues = PaddingValues(0.dp)
){
    NavHost(
        navController = navController,
        startDestination = LlistaArtistes,
        modifier = Modifier.padding(paddingValues)
    ){
        composable<LlistaArtistes>{
            LlistaA(llista = obtenDadesDelsArtistes())
        }
        composable<LlistaDiscos>{
            LlistaD(llista = obtenDadesDelsDiscos())
        }
        composable<LlistaConcerts>{
            LlistaC(llista = obtenDadesDelsConcerts())
        }
    }
}