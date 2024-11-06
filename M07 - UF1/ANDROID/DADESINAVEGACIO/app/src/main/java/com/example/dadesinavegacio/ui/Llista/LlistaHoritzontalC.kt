package com.example.dadesinavegacio.ui.Llista

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dadesinavegacio.Dades.Conserts
import com.example.dadesinavegacio.Dades.Discos
import com.example.dadesinavegacio.Model.Consert
import com.example.dadesinavegacio.Model.Disco
import com.example.dadesinavegacio.ui.Componibles.ComponibleHoritzontalC
import com.example.dadesinavegacio.ui.Componibles.ComponibleHoritzontalD

@Composable
fun LlistaHoritzontalC(
    conserts : List<Consert> = Conserts.obtenDadesDelsConserts())
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .background(Color(231, 227, 137 ))
            .padding(10.dp)
    ) {
        items(conserts){
            ComponibleHoritzontalC(it)
        }
    }
}