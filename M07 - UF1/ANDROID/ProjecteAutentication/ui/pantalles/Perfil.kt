package com.montilivi.projecteautentificacio.ui.pantalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.montilivi.projecteautentificacio.R
import com.montilivi.projecteautentificacio.analitiques.analituquesManager
import com.montilivi.projecteautentificacio.autentificacio.autentificationManager
import kotlinx.coroutines.launch

@Composable
fun Perfil(
    manegadorAnalitiques: analituquesManager,
    manegadorAutentificacio: autentificationManager,
    navegacioInici: () -> Any ={}
)
{
    val ambitCorrutin = rememberCoroutineScope()
    val user = manegadorAutentificacio.current_user()
    manegadorAnalitiques.registraVisitaPantalla("PantallaPerfil")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(210, 183, 196, 255))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Image(
                painter = painterResource(id = R.drawable.perfil),
                contentDescription = null,
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
            )
        }
        Spacer(Modifier.height(70.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            if (user?.photoUrl != null){
                Image(
                    painter = rememberImagePainter(user.photoUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp)
                )
            }
            else{
                Image(
                    painter = painterResource(id = R.drawable.usuarioanonimo),
                    contentDescription = null,
                    modifier = Modifier
                        .height(90.dp)
                        .width(90.dp))
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "EMAIL:",
                color = Color(75,21,53),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            TextField(
                value = user?.email?:"USRUARIO ANONIMO",
                onValueChange = {},
                label = {},
                textStyle = TextStyle(
                    color = Color(75, 21, 53),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                ),
                modifier = Modifier
                    .weight(1f),
                enabled = false
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "NOMBRE:",
                color = Color(75,21,53),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            TextField(
                value = user?.displayName?:"USRUARIO ANONIMO",
                onValueChange = {},
                label = {}
                ,
                textStyle = TextStyle(
                    color = Color(75, 21, 53),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                ),
                modifier = Modifier
                    .weight(1f),
                enabled = false
            )
        }
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    manegadorAnalitiques.registraClickBtn("CERRAR SESION")
                    ambitCorrutin.launch {
                        manegadorAutentificacio.close_session()
                        navegacioInici()
                    }
                },
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(209, 131, 169),
                    contentColor = Color(75, 21, 53),
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                )
            ) {
                Text("CERRAR SESION")
            }
        }
    }
}

