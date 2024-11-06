package com.example.dadesinavegacio.ui.Pantalles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PantallaPrincipal(onArtistesClick:() -> Unit, onConcertsClick:() -> Unit, onDiscosClick:() -> Unit)
{
    Column(modifier = Modifier
        .background(Color(200, 141, 186))
        .fillMaxSize()
        .padding(5.dp),
        verticalArrangement = Arrangement.Center)
    {
        Row(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())
        {
            Button(
                onClick = onArtistesClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(Color.White))
            {
                Text(
                    text = "ARTISTES",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
            }
        }
        Row(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())
        {
            Button(
                onClick = onDiscosClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(Color.White))
            {
                Text(
                    text = "DISCOS",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
            }
        }
        Row(modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())
        {
            Button(
                onClick = onConcertsClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(Color.White))
            {
                Text(
                    text = "CONCERTS",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
            }
        }
    }
}