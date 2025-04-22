package com.montilivi.projecteautentificacio.ui.pantalles

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DoDisturb
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.montilivi.llistacompra.model.llistaCompra
import com.montilivi.llistacompra.model.usuari
import com.montilivi.projecteautentificacio.analitiques.analituquesManager
import com.montilivi.projecteautentificacio.autentificacio.autentificationManager
import com.montilivi.projecteautentificacio.ui.viewmodels.viewmodelLlistaCompra

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LlistaCompra(viewModel: viewmodelLlistaCompra = viewModel(), autentificationManager: autentificationManager, analituquesManager: analituquesManager) {
    val estat = viewModel.estat.collectAsState()
    val correu = autentificationManager.current_user()?.email.toString()
    viewModel.obtenirUsuari(correu)
    val usuari = estat.value.ususariActual
    var mostraDialegAfegeixLlistaCompra by remember { mutableStateOf(false) }
    var mostraDialegActualitzaLlistaCompra by remember { mutableStateOf(false) }
    var llistaEditat by remember { mutableStateOf(llistaCompra()) }
    val afegeixLLista = { llista: llistaCompra, user: usuari -> viewModel.afegeixLlista(llista, user) }
    val nomUsuari = { id: String -> viewModel.obtenirNomUsuari(id) }

    val eliminaLlista = {llista: llistaCompra, id: String -> viewModel.eliminaLlista(llista = llista, id) }
    val actualizaLlistaCompra = { llista: llistaCompra -> viewModel.modificaLlista(llista) }
    val afegirUsuari = {llista: llistaCompra, nom: String -> viewModel.afegeixUsuariALlista(llista, nom)}
    val eliminarUsuari = {llista: llistaCompra, id: String -> viewModel.treureUsuariDeLlista(llista, id)}
    val editaLlistaCompra = { llistaCompra: llistaCompra ->
        llistaEditat = llistaCompra
        mostraDialegActualitzaLlistaCompra = true
    }
    val onDialogDismissed = {
        mostraDialegAfegeixLlistaCompra = false
        mostraDialegActualitzaLlistaCompra = false
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                mostraDialegAfegeixLlistaCompra = true
            }) { Icon(Icons.Filled.Add, "Floating action button.") }
            if (mostraDialegAfegeixLlistaCompra) {
                DialegAfegeixLlistaCompra(afegeixLLista, onDialogDismissed, usuari)
            }
            if (mostraDialegActualitzaLlistaCompra) {
                DialegActualitzaLlistaCompra(llistaEditat, actualizaLlistaCompra, onDialogDismissed, afegirUsuari, eliminarUsuari, nomUsuari)
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(estat.value.llistes) { item ->
                ElementLlistaCompra(item, eliminaLlista, editaLlistaCompra, usuari)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ElementLlistaCompra(
    llistaCompra: llistaCompra= llistaCompra(
        id = "id",
        nom = "nom",
        usuarisAutoritzats = mutableListOf(),
        lineasList = mutableListOf()
    ),
    eliminaLlista: (llistaCompra, String) -> Unit = { _, _ -> },
    editaLlista: (llistaCompra) -> Unit={},
    usuari: usuari = usuari()
)
{
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)
    ){

        Text(
            text = llistaCompra.nom,
            modifier = Modifier.padding(8.dp)
                .weight(1F),
            style = MaterialTheme.typography.displayMedium,
            color = Color.Black
        )
        IconButton(
            onClick = {eliminaLlista(llistaCompra, usuari.id)},
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Filled.Delete, "Elimina", tint = MaterialTheme.colorScheme.onError)
        }
        IconButton(
            onClick = { editaLlista(llistaCompra)},
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            Icon(Icons.Filled.Edit, "Modifica", tint = MaterialTheme.colorScheme.onSecondary)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DialegAfegeixLlistaCompra(afegeixLlista: (llistaCompra, usuari) -> Unit, onDialogDismissed: () -> Unit, usuari: usuari) {
    var nom by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Crea nova Llista") },
        confirmButton = {
            if (nom.isNotEmpty()) {
                Button(
                    onClick = {
                        val llistaCompraNou = llistaCompra(
                            id = "",
                            nom = nom,
                            usuarisAutoritzats = mutableListOf(),
                            lineasList = mutableListOf()
                        )
                        afegeixLlista(llistaCompraNou, usuari)
                        nom = ""
                        onDialogDismissed()
                    }
                ) {
                    Text(text = "Afegeix")
                }
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDialogDismissed()
                }
            ) {
                Text(text = "Cancel·la")
            }
        },
        text = {
            Column {
                TextField(
                    value = nom,
                    onValueChange = { nom = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nom de la llista") }
                )

            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DialegActualitzaLlistaCompra(llista: llistaCompra, actualitzaLlistaCompra: (llistaCompra) -> Unit, onDialogDismissed: () -> Unit, afegirUsuari:(llistaCompra,String) -> Unit, eliminaUsuari: (llistaCompra, String) -> Unit, nomUsuari:(String)-> String) {
    var nom by remember { mutableStateOf(llista.nom) }
    var usuaris = llista.usuarisAutoritzats
    var nouUsuari by remember { mutableStateOf("") }
    val controladoPaletaDeColors = rememberColorPickerController()
    var expandir by remember { mutableStateOf(false) }
    var opcioSelecionada by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDialogDismissed,
        title = { Text(text = "Modifica la llista") },
        confirmButton = {
            if (nom.isNotEmpty()) {
                Button(
                    onClick = {
                        onDialogDismissed()
                    }
                ) {
                    Text(text = "Modifica")
                }
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDialogDismissed()
                }
            ) {
                Text(text = "Cancel·la")
            }
        },
        text = {
            Column {
                Text(
                    text = nom,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp))
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nom,
                    onValueChange = { nom = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nom de llista") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    TextField(
                        modifier = Modifier
                            .weight(3F),
                        value = nouUsuari,
                        onValueChange = { nouUsuari = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                        maxLines = 1,
                        label = { Text(text = "Afegir usuari") }
                    )
                    Spacer(Modifier.width(5.dp))
                    IconButton(
                        onClick = {
                            afegirUsuari(llista, nouUsuari)
                            actualitzaLlistaCompra(llista)
                                  },
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .alpha(1F)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            "Add", tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }
        }
    )
}