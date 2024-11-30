package cat.institutmontivi.decissorviewmodel.ui.pantalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.institutmontivi.decissorviewmodel.R
import cat.institutmontivi.decissorviewmodel.dades.PreferenciesDataStore
import cat.institutmontivi.decissorviewmodel.ui.common.Boto
import cat.institutmontivi.decissorviewmodel.ui.viewmodels.ViewModelCaraOCreu


@Preview
@Composable
fun PantallaCaraOCreu (viewModel: ViewModelCaraOCreu = viewModel())
{
    val  estat = viewModel.estat
    val temps by PreferenciesDataStore(LocalContext.current).getTeampsCaraOCreu.collectAsState(initial = 0)
    Column(
        Modifier
            .padding(32.dp)
            .fillMaxSize())
    {
        var imatge = R.drawable.question
        if (estat.resultat == 0){
            imatge = R.drawable.question
        }
        else if (estat.resultat == 1){
            imatge = R.drawable.cara
        }
        else if (estat.resultat == 2){
            imatge = R.drawable.creu
        }
        Image(painter = painterResource(id = imatge), contentDescription = null,
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally), contentScale = ContentScale.Fit)

        if(estat.estatSortejant){
            Boto(
                modifier = Modifier
                    .weight(0.75F)
                    .fillMaxHeight(),
                text = "Sorteja",
                clic = {viewModel.sorteja(temps)})
        }
        else{
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp)
            )
        }

    }
}
