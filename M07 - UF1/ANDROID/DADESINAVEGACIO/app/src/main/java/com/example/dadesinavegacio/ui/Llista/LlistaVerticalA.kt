package com.example.dadesinavegacio.ui.Llista

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.dadesinavegacio.Dades.Artistes
import com.example.dadesinavegacio.Model.Artista
import com.example.dadesinavegacio.ui.Componibles.ComponibleVerticalA

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LlistaVerticalA(artistes : List<Artista> = Artistes.obtenDadesDelsArtistes()) {
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        stickyHeader() {
            Text("LLISTA DE ARTISTES", style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                textAlign = TextAlign.Center)
        }
        items(artistes){
            ComponibleVerticalA(it)
        }
    }
}