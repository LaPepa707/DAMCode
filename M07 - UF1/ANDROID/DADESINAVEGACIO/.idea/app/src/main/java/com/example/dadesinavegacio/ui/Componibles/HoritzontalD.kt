package com.example.dadesinavegacio.ui.Componibles

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun ComponibleHoritzontalD(disco: Disco, onPantallaDetalladaD: (Int) -> Unit= {})
{
    Box(modifier = Modifier
        .wrapContentHeight()
        .clickable(onClick = {onPantallaDetalladaD(disco.id)})
        .wrapContentWidth()
        .background(Color(247, 170, 244))
    ){
        Row(modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
            verticalAlignment =  Alignment.CenterVertically

        ){
            AsyncImage(
                model = ImageRequest
                    .Builder(LocalContext.current)
                    .data(disco.foto)
                    .crossfade(durationMillis = 2000)
                    .build(),
                contentDescription = null,
                onError = { Log.e("PATATA", it.toString())},
                modifier = Modifier
                    .weight(0.4f)
                    .height(40.dp)
            )
            Text(
                text = disco.nom,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.weight(0.6f).padding(5.dp),
                color = Color.Black
            )
        }

    }
}