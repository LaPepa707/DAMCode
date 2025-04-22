package com.montilivi.llistacompra.navegacio

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Start
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
object PantallaIniciSessio
@Serializable
object PantallaPerfil
@Serializable
object PantallaPortada
@Serializable
object PantallaLlistaCompra
@Serializable
object PantallaCategories
@Serializable
object PantallaRegitrar

data class CategoriaNavegacio<T:Any>(
    val ruta: T,
    val iconaSeleccionada: ImageVector,
    val iconaNoSeleccionada: ImageVector,
    val titol: String
)

val categoriaNavegacio = listOf(
    CategoriaNavegacio(
        ruta = PantallaLlistaCompra,
        iconaSeleccionada = Icons.Filled.TaskAlt,
        iconaNoSeleccionada = Icons.Filled.TaskAlt,
        titol = "LLISTA COMPRA"
    ),
    CategoriaNavegacio(
        ruta = PantallaCategories,
        iconaSeleccionada = Icons.Filled.Category,
        iconaNoSeleccionada = Icons.Filled.Category,
        titol = "CATEGORIES"
    ),
    CategoriaNavegacio(
        ruta = PantallaPerfil,
        iconaSeleccionada = Icons.Filled.Person,
        iconaNoSeleccionada = Icons.Filled.Person,
        titol = "PERFIL"
    ),
    CategoriaNavegacio(
        ruta = PantallaPortada,
        iconaSeleccionada = Icons.Filled.Start,
        iconaNoSeleccionada = Icons.Filled.Start,
        titol = "PORTADA"
    )
)