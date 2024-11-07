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
object CategoriaArtistes
@Serializable
object CategoriaConcerts
@Serializable
object CategoriaDiscos
@Serializable
object LlistaVA
@Serializable
object LlistaHA
@Serializable
data class PantallaDA(val dada: Int)
@Serializable
object LlistaVC
@Serializable
object LlistaHC
@Serializable
data class PantallaDC(val dada: Int)
@Serializable
object LlistaVD

@Serializable
object LlistaHD
@Serializable
data class PantallaDD(val dada: Int)
@Serializable
object MenuArtiste
@Serializable
object MenuDisc
@Serializable
object MenuConsert


data class CategoriaDeNavegacio<T:Any>(
    val ruta:T,
    val iconaNoSeleccionada: ImageVector,
    val iconaSeleccionada: ImageVector,
    val titol: String
)
val categoriesDeNavegacio = listOf(
    CategoriaDeNavegacio(
        ruta = CategoriaArtistes,
        iconaSeleccionada = Icons.Filled.Person,
        iconaNoSeleccionada = Icons.Outlined.Person,
        titol = "Artistes"
    ),
    CategoriaDeNavegacio(
        ruta = CategoriaDiscos,
        iconaSeleccionada = Icons.Filled.AddCircle,
        iconaNoSeleccionada = Icons.Outlined.AddCircle,
        titol = "Discos"
    ),
    CategoriaDeNavegacio(
        ruta = CategoriaConcerts,
        iconaSeleccionada = Icons.Filled.LocationOn,
        iconaNoSeleccionada = Icons.Outlined.LocationOn,
        titol = "Concerts"
    )

)
