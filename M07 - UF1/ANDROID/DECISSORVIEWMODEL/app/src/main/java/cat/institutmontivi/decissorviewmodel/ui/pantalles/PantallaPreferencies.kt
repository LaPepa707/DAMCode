package cat.institutmontivi.decissorviewmodel.ui.pantalles


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.institutmontivi.decissorviewmodel.dades.PreferenciesDataStore
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Preview
@Composable
fun PantallaPreferencies ()
{
    val preferencies = PreferenciesDataStore(LocalContext.current)
    val tempsCaraOCreu by preferencies.getTeampsCaraOCreu.collectAsState(initial = 0)
    val tempsTriaNumero by preferencies.getTeampsTriaNumero.collectAsState(initial = 0)
    val minimTriaNumero by preferencies.getMinimTriaNumero.collectAsState(initial = 0)
    val maximTriaNumero by preferencies.getMaximTriaNumero.collectAsState(initial = 0)
    val  ambitCortina = rememberCoroutineScope()
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Preferències",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Divider(Modifier
            .fillMaxSize()
            .height(10.dp)
            .background(MaterialTheme.colorScheme.onSurfaceVariant)
            .verticalScroll(rememberScrollState()))
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) {
                Text(
                    text = "Cara o Creu",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Divider(Modifier
                    .fillMaxSize()
                    .height(10.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant)
                    .verticalScroll(rememberScrollState()))
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Hola",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Divider(Modifier
                    .fillMaxSize()
                    .height(10.dp)
                    .background(MaterialTheme.colorScheme.onSurfaceVariant)
                    .verticalScroll(rememberScrollState()))
                Spacer(Modifier.height(10.dp))
                Row(

                ) {
                    Slider(
                        modifier =
                        Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .weight(1f),
                        valueRange = 0F..5000F,
                        value = tempsCaraOCreu.toFloat(),
                        onValueChange = {
                            ambitCortina.launch{
                                preferencies.setTempsCaraOCreu(it.toLong())
                            }

                        }
                    )
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp),
                        text = "${tempsCaraOCreu}",
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

}


