package com.example.dadesinavegacio.ui.Pantalles

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.dadesinavegacio.Dades.Artistes
import com.example.dadesinavegacio.Dades.Conserts
import com.example.dadesinavegacio.Model.Artista
import com.example.dadesinavegacio.Model.Consert


@Composable
fun PantallaDetalladaC(int : Int)
{
    Box(modifier = Modifier
        .fillMaxSize()
    )
    {
        val consert : Consert = Conserts.obtenirConsertById(int)
        Column(modifier = Modifier
            .background(Color(251, 180, 252))
            .fillMaxWidth()
            .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Row ( modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ){
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(consert.foto)
                        .crossfade(durationMillis = 2000)
                        .build(),
                    contentDescription = null,
                    onError = { Log.e("PATATA", it.toString())},
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                Column(//col1
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                ) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp, 10.dp, 5.dp)
                        .background(Color.White)
                    ){
                        Text(text = "NOM: ${consert.nom}", modifier = Modifier
                            .padding(7.dp),
                            style = MaterialTheme.typography.labelSmall)
                    }
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(10.dp, 5.dp, 10.dp, 5.dp)
                        .background(Color.White)
                    ){
                        Text(text = "DESCRIPCIÓ: ${consert.descripcio}", modifier = Modifier
                            .padding(7.dp),
                            style = MaterialTheme.typography.labelSmall)
                    }
                }
                Column(//col2
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentHeight()
                ) {
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp, 10.dp, 0.dp)
                        .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        val current = consert.numeroEntradesComprades
                        val max = consert.capacitat

                        val progress = if(max > 0) current.toFloat() / max else 0f
                        Text(
                            text = "Plazas ocupadas: ${(progress * 100).toInt()}%",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.weight(0.3f).padding(5.dp),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        LinearProgressIndicator(
                            progress = progress.coerceIn(0f, 1f),
                            modifier = Modifier.weight(0.7f).height(20.dp).padding(5.dp, 0.dp, 10.dp, 0.dp),
                            color = Color(121, 157, 229)
                        )
                    }
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(0.dp, 5.dp, 10.dp, 0.dp)
                        .background(Color.White)
                    ){
                        Text(text = "PAIS: ${consert.pais}", modifier = Modifier
                            .padding(7.dp),
                            style = MaterialTheme.typography.labelSmall)
                    }
                    Row(modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .background(Color.White)
                    ){
                        Text(text = "ARTISTA: ${consert.artista}", modifier = Modifier
                            .padding(7.dp),
                            style = MaterialTheme.typography.labelSmall)
                    }
                }

            }
        }
    }
}