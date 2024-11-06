package com.example.dadesinavegacio.ui.Componibles

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.dadesinavegacio.Dades.Artistes
import com.example.dadesinavegacio.Dades.Discos
import com.example.dadesinavegacio.Model.Artista
import com.example.dadesinavegacio.Model.Disco

@Composable
fun ComponibleVerticalD(disco: Disco = Discos.obtenirDisc()) {
    Box() {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .wrapContentHeight()
                .fillMaxWidth()
                .background(Color(247, 170, 244))
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(disco.foto)
                        .crossfade(durationMillis = 2000)
                        .build(),
                    contentDescription = null,
                    onError = { Log.e("PATATA", it.toString()) },
                    modifier = Modifier
                        .padding(10.dp)
                        .height(70.dp)
                        .weight(1f),
                    contentScale = ContentScale.Crop
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp, 0.dp, 0.dp, 8.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nom: ${disco.nom}",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(0.5f).padding(5.dp),
                    color = Color.Black
                )

                Text(
                    text = "Artista: ${disco.artista}",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.weight(0.5f).padding(5.dp),
                    color = Color.Black
                )
            }

            Row(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Descripció: ${disco.descripcio}",
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(5.dp).fillMaxWidth(),
                    color = Color.Black
                )
            }
        }
    }
}