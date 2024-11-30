package cat.institutmontivi.decissorviewmodel.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import cat.institutmontivi.decissorviewmodel.navegacio.DestinacioOracleResposta

class ViewModelOracleResposta(saverdStateHandler: SavedStateHandle): ViewModel() {
    var estat by mutableStateOf(EstatOracleResposta())
        private set
    init {
        val arg = saverdStateHandler.toRoute<DestinacioOracleResposta>()<DestinacioOracleResposta>()
        estat = estat.copy(pregunta = arg.pregunta)
    }
    fun respon(){//faltan cosas
        estat = estat.copy(resposta = "NO")
    }
}
data class EstatOracleResposta(
    val resposta : String = "",
    val pregunta : String = ""
)
