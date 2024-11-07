package com.example.dadesinavegacio.ui.Llista

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dadesinavegacio.Dades.Artistes
import com.example.dadesinavegacio.Dades.Discos
import com.example.dadesinavegacio.Model.Artista
import com.example.dadesinavegacio.Model.Disco
import com.example.dadesinavegacio.ui.Componibles.ComponibleHoritzontalA
import com.example.dadesinavegacio.ui.Componibles.ComponibleHoritzontalD

@Composable
fun LlistaHoritzontalD(
    discos : List<Disco> = Discos.obtenDadesDelsDiscos(), onClickElement: (Int) -> Unit = {})
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(Color(231, 227, 137 ))
            .padding(10.dp)
    ) {
        items(discos){disc ->
            ComponibleHoritzontalD(disc, onPantallaDetalladaD = {onClickElement(disc.id)})
        }
    }
}