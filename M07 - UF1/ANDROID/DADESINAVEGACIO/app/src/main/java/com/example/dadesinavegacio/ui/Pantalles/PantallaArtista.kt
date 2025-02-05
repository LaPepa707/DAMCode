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
fun PantallaArtista(onLlistaVAClick:()-> Unit, onLlistaHAClick:()-> Unit, onPantallaDAClick:()-> Unit)
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
                onClick = onLlistaVAClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(Color.White))
            {
                Text(
                    text = "LLISTA VERTICAL",
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
                onClick = onLlistaHAClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(Color.White))
            {
                Text(
                    text = "LLISTA HORITZONTAL",
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
                onClick = onPantallaDAClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(Color.White))
            {
                Text(
                    text = "PANTALLA DETALLADA",
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