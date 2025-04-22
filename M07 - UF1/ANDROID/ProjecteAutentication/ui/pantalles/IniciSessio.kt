package com.montilivi.projecteautentificacio.ui.pantalles

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montilivi.projecteautentificacio.R
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.montilivi.llistacompra.dades.DAOFactory
import com.montilivi.llistacompra.model.report_autentification
import com.montilivi.llistacompra.model.usuari
import com.montilivi.projecteautentificacio.analitiques.analituquesManager
import com.montilivi.projecteautentificacio.autentificacio.autentificationManager
import com.montilivi.projecteautentificacio.ui.viewmodels.viewmodelUsuari
import kotlinx.coroutines.launch


@Composable
fun IniciSessio(
    onClickRegistrar: () -> Unit = {},
    manegadorAutentificacio: autentificationManager,
    menegadorAnalitiques: analituquesManager,
    navegacioInici:()-> Any ={},
    viewmodelUsuari: viewmodelUsuari = viewModel()
)
{
    val estat = viewmodelUsuari.estat.collectAsState()
    menegadorAnalitiques.registraVisitaPantalla("PantallaIniciSessio")
    val ambitCorrutin = rememberCoroutineScope()
    var nom_correu by remember{ mutableStateOf("") }
    var contra_correu by remember{ mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(243, 200, 221))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.firebaselogo),
                contentDescription = null,
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
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
                value = nom_correu,
                onValueChange = {value->
                    nom_correu = value
                },
                label = { Text("Email") }
                ,
                textStyle = TextStyle(
                    color = Color(75, 21, 53),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                ),
                modifier = Modifier
                    .weight(1f)
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
                text = "CONTRASEÑA:",
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
                value = contra_correu,
                onValueChange = {value->
                    contra_correu = value
                },
                label = { Text("Contraseña") }
                ,
                textStyle = TextStyle(
                    color = Color(75, 21, 53),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                ),
                modifier = Modifier
                    .weight(1f)
            )
        }
        Spacer(Modifier.height(20.dp))
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
                    menegadorAnalitiques.registraClickBtn("Inicia sessió")
                    ambitCorrutin.launch{
                        val noError = manegadorAutentificacio.start_with_email_password(nom_correu, contra_correu)
                        if(noError is report_autentification.Exit && noError.dades){
                            val db = DAOFactory.obtenirDAOUsuari(DAOFactory.TipusDB.FIREBASE)
                            val usuari = usuari(
                                id ="",
                                nom = manegadorAutentificacio.current_user()?.displayName ?: "",
                                cognoms = "",
                                correu = manegadorAutentificacio.current_user()?.email ?: "",
                                fotoId = manegadorAutentificacio.current_user()?.photoUrl.toString()
                            )
                            val resposta : report_autentification<Boolean> = db.afegeixUsuari(usuari)
                            if(resposta is report_autentification.Exit){
                                Log.d("PantallaIniciSessio", "Usuari afegit")
                                viewmodelUsuari.afegeixUsuari(usuari)
                            }
                        }
                        else if(noError is report_autentification.Fracas){
                            Log.d("PantallaIniciSessio", "Error afegint usuari")
                            estat.value.missatgeError = "Error afegint usuari ${noError.msgError}"
                        }
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
                Text("INICIAR SESIÓN CON CORREO")
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
            Button(
                onClick = {
                    menegadorAnalitiques.registraClickBtn("Inicia sessió")
                    ambitCorrutin.launch{
                        val noError = manegadorAutentificacio.inici_Sessio_Google()
                        if(noError){
                            val db = DAOFactory.obtenirDAOUsuari(DAOFactory.TipusDB.FIREBASE)
                            val usuari = usuari(
                                id ="",
                                nom = manegadorAutentificacio.current_user()?.displayName ?: "",
                                cognoms = "",
                                correu = manegadorAutentificacio.current_user()?.email ?: "",
                                fotoId = manegadorAutentificacio.current_user()?.photoUrl.toString()
                            )
                            val resposta : report_autentification<Boolean> = db.afegeixUsuari(usuari)
                            if(resposta is report_autentification.Exit){
                                Log.d("PantallaIniciSessio", "Usuari afegit")
                                viewmodelUsuari.afegeixUsuari(usuari)
                            }
                            else{
                                Log.d("PantallaIniciSessio", "Error afegint usuari ${usuari.correu}")
                                estat.value.missatgeError = "Error afegint usuari ${usuari.correu}"
                            }
                        }
                        navegacioInici()
                    }
                },
                modifier = Modifier
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(113, 85, 122),
                    contentColor = Color(243, 200, 221),
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.LightGray
                )
            ) {
                Text("INICIAR SESIÓN CON GOOGLE")
                Spacer(Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.googlelogo),
                    contentDescription = null,
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
            }
        }
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "REGISTRARSE",
                color = Color(75,21,53),
                modifier = Modifier
                    .clickable(onClick = onClickRegistrar)
            )
        }
        Spacer(Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = estat.value.missatgeError,
                color = Color(75,21,53)
            )

        }
        
    }
}