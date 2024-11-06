package com.example.dadesinavegacio.Navegacio

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
object Principal
@Serializable
object Artistes
@Serializable
object Concerts
@Serializable
object Discos
@Serializable
object LlistaVA
@Serializable
object LlistaHA
@Serializable
object PantallaDA
@Serializable
object LlistaVC
@Serializable
object LlistaHC
@Serializable
object PantallaDC
@Serializable
object LlistaVD
@Serializable
object LlistaHD
@Serializable
object PantallaDD

data class CategoriaDeNavegacio<T:Any>(
    val ruta:T,
    val iconaNoSeleccionada: ImageVector,
    val iconaSeleccionada: ImageVector,
    val titol: String
)
val categoriesDeNavegacio = listOf(
    CategoriaDeNavegacio(
        ruta = Artistes,
        iconaSeleccionada = Icons.Filled.Person,
        iconaNoSeleccionada = Icons.Outlined.Person,
        titol = "Artistes"
    ),
    CategoriaDeNavegacio(
        ruta = Discos,
        iconaSeleccionada = Icons.Filled.AddCircle,
        iconaNoSeleccionada = Icons.Outlined.AddCircle,
        titol = "Discos"
    ),
    CategoriaDeNavegacio(
        ruta = Concerts,
        iconaSeleccionada = Icons.Filled.LocationOn,
        iconaNoSeleccionada = Icons.Outlined.LocationOn,
        titol = "Concerts"
    )

)
