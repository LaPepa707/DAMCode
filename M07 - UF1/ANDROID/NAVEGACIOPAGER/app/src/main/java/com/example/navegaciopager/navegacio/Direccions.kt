package com.example.navegaciopager.navegacio

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object LlistaArtistes
@Serializable
object LlistaConcerts
@Serializable
object LlistaDiscos

data class CategoriaDeNavegacio<T:Any>(
    val ruta:T,
    val iconaNoSeleccionada: ImageVector,
    val iconaSeleccionada: ImageVector,
    val titol: String
)
val categoriesDeNavegacio = listOf(
    CategoriaDeNavegacio(
        ruta = LlistaArtistes,
        iconaSeleccionada = Icons.Filled.Person,
        iconaNoSeleccionada = Icons.Outlined.Person,
        titol = "Artistes"
    ),
    CategoriaDeNavegacio(
        ruta = LlistaDiscos,
        iconaSeleccionada = Icons.Filled.AddCircle,
        iconaNoSeleccionada = Icons.Outlined.AddCircle,
        titol = "Discos"
    ),
    CategoriaDeNavegacio(
        ruta = LlistaConcerts,
        iconaSeleccionada = Icons.Filled.LocationOn,
        iconaNoSeleccionada = Icons.Outlined.LocationOn,
        titol = "Concerts"
    )

)
