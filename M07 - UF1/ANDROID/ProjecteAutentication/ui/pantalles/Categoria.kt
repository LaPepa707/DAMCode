package com.montilivi.projecteautentificacio.ui.pantalles

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.montilivi.llistacompra.model.categoria
import com.montilivi.projecteautentificacio.ui.viewmodels.viewmodelCategoria

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Categoria(viewModel: viewmodelCategoria = viewModel()) {
    val estat = viewModel.estat.collectAsState()
    var mostraDialegAfegeixCategoria by remember { mutableStateOf(false) }
    var mostraDialegActualitzaCategoria by remember { mutableStateOf(false) }
    var categoriaEditat = categoria()

    val afegeixCategoria = { categoria: categoria -> viewModel.afegeixCategoria(categoria) }
    val eliminaCategoria = { id: String -> viewModel.eliminaCategoria(id) }
    val actualizaCategoria = { categoria: categoria -> viewModel.actualitzaCategoria(categoria) }
    val editaCategoria = { categoria: categoria ->
        categoriaEditat = categoria
        mostraDialegActualitzaCategoria = true
    }

    val onDialogDismissed = {
        mostraDialegAfegeixCategoria = false
        mostraDialegActualitzaCategoria = false
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                mostraDialegAfegeixCategoria = true
            }) { Icon(Icons.Filled.Add, "Floating action button.") }
            if (mostraDialegAfegeixCategoria) {
                DialegAfegeixEstat(afegeixCategoria, onDialogDismissed)
            }
            if (mostraDialegActualitzaCategoria) {
                DialegActualitzaEstat(categoriaEditat, actualizaCategoria, onDialogDismissed)
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(estat.value.categories) { item ->
                ElementEstat(item, eliminaCategoria, editaCategoria)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ElementEstat(
    categoria: categoria = categoria(
        id = "id",
        nom = "Nom",
        colorFons = "#FF444444",
        colorText = "#FFBBBBBB"
    ),
    eliminaCategoria: (String) -> Unit={},
    editaCategoria: (categoria) -> Unit={}
)
{
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color= Color(categoria.colorFons.toColorInt()))
    ){

        Text(
            text = categoria.nom,
            modifier = Modifier.padding(8.dp)
                .weight(1F),
            style = MaterialTheme.typography.displayMedium,
            color = Color(categoria.colorText.toColorInt())
        )
        IconButton(
            onClick = {eliminaCategoria(categoria.id)},
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Filled.Delete, "Elimina", tint = MaterialTheme.colorScheme.onError)
        }
        IconButton(
            onClick = { editaCategoria(categoria)},
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
fun DialegAfegeixEstat(afegeixEstat: (categoria) -> Unit, onDialogDismissed: () -> Unit) {
    var nom by remember { mutableStateOf("") }
    var colorText by remember { mutableStateOf("#FF000000") }
    var colorFons by remember { mutableStateOf("#FFFFFFFF") }
    val controladoPaletaDeColors = rememberColorPickerController()
    var seleccionaFons by remember { mutableStateOf(false) }


    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Crea nova Categoria") },
        confirmButton = {
            if (nom.isNotEmpty()) {
                Button(
                    onClick = {
                        val estatNou = categoria(
                            nom = nom,
                            colorText = colorText,
                            colorFons = colorFons
                        )
                        afegeixEstat(estatNou)
                        nom = ""
                        colorText = "#FF000000"
                        colorFons = "#FFFFFFFF"
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
                Text(
                    text = nom,
                    modifier = Modifier.padding(8.dp)
                        .fillMaxWidth()
                        .background(Color(colorFons.toColorInt())),
                    style = MaterialTheme.typography.displayMedium,
                    color = Color(colorText.toColorInt())
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth().height(2.dp))
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nom,
                    onValueChange = { nom = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nom de l'estat") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row()
                {
                    TextField(
                        modifier = Modifier
                            .weight(3F),
                        value = colorFons,
                        onValueChange = { colorFons = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                        maxLines = 1,
                        label = { Text(text = "Color del fons") }

                    )
                    IconButton(
                        onClick = { seleccionaFons = true },
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .alpha(if (seleccionaFons) 1F else 0.7F)

                    ) {
                        Icon(
                            if (seleccionaFons)
                                Icons.Filled.Edit
                            else
                                Icons.Filled.DoDisturb,
                            "Modifica", tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                Row()
                {
                    TextField(
                        modifier = Modifier
                            .weight(3F),
                        value = colorText,
                        onValueChange = { colorText = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                        maxLines = 1,
                        label = { Text(text = "Color del text") }
                    )
                    IconButton(
                        onClick = { seleccionaFons = false },
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .alpha(if (!seleccionaFons) 1F else 0.7F)

                    ) {
                        Icon(
                            if (seleccionaFons == false)
                                Icons.Filled.Edit
                            else
                                Icons.Filled.DoDisturb,
                            "Modifica", tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(10.dp),
                    controller = controladoPaletaDeColors,
                    onColorChanged = { colorEnvelope: ColorEnvelope ->
                        if (seleccionaFons) {
                            colorFons = "#${colorEnvelope.hexCode}"
                        } else {
                            colorText = "#${colorEnvelope.hexCode}"
                        }
                    }
                )
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DialegActualitzaEstat(categoria: categoria, actualitzaCategoria: (categoria) -> Unit, onDialogDismissed: () -> Unit) {
    var nom by remember { mutableStateOf(categoria.nom) }
    var colorText by remember { mutableStateOf(categoria.colorText) }
    var colorFons by remember { mutableStateOf(categoria.colorFons) }
    val controladoPaletaDeColors = rememberColorPickerController()
    var seleccionaFons by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDialogDismissed,
        title = { Text(text = "Modifica la categoria") },
        confirmButton = {
            if (nom.isNotEmpty()) {
                Button(
                    onClick = {
                        val estatNou = categoria(
                            id = categoria.id,
                            nom = nom,
                            colorText = colorText,
                            colorFons = colorFons
                        )
                        actualitzaCategoria(estatNou)
                        nom = ""
                        colorText = "#FF000000"
                        colorFons = "#FFFFFFFF"
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
                    modifier = Modifier.padding(8.dp)
                        .fillMaxWidth()
                        .background(Color(colorFons.toColorInt())),
                    style = MaterialTheme.typography.displayMedium,
                    color = Color(colorText.toColorInt())
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(modifier = Modifier.fillMaxWidth().height(2.dp))
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = nom,
                    onValueChange = { nom = it },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    label = { Text(text = "Nom de l'estat") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row()
                {
                    TextField(
                        modifier = Modifier
                            .weight(3F),
                        value = colorFons,
                        onValueChange = { colorFons = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                        maxLines = 1,
                        label = { Text(text = "Color del fons") }

                    )
                    IconButton(
                        onClick = { seleccionaFons = true },
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .alpha(if(seleccionaFons) 1F else 0.7F)

                    ){
                        Icon(
                            if (seleccionaFons)
                                Icons.Filled.Edit
                            else
                                Icons.Filled.DoDisturb,
                            "Modifica", tint = MaterialTheme.colorScheme.onSecondary)
                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                Row()
                {
                    TextField(
                        modifier = Modifier
                            .weight(3F),
                        value = colorText,
                        onValueChange = { colorText = it },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Ascii),
                        maxLines = 1,
                        label = { Text(text = "Color del text") }
                    )
                    IconButton(
                        onClick = { seleccionaFons = false },
                        modifier = Modifier
                            .weight(1F)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .alpha(if(!seleccionaFons) 1F else 0.7F)

                    ){
                        Icon(
                            if (seleccionaFons==false)
                                Icons.Filled.Edit
                            else
                                Icons.Filled.DoDisturb,
                            "Modifica", tint = MaterialTheme.colorScheme.onSecondary)
                    }
                }
                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(10.dp),
                    controller = controladoPaletaDeColors,
                    onColorChanged = { colorEnvelope: ColorEnvelope ->
                        if (seleccionaFons){
                            colorFons = "#${colorEnvelope.hexCode}"
                        }
                        else
                        {
                            colorText = "#${colorEnvelope.hexCode}"
                        }
                    }
                )
            }
        }
    )
}