package cat.institutmontivi.decissorviewmodel.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VIewModelTriaNumero : ViewModel(){
    var estat by mutableStateOf(EstatTriaNumero())
        private set
    init {
        estat = estat.copy(valorTriat = 0, estaSortejant = false)
    }

    fun Sorteja(minim: Int, maxim: Int, temps: Long){
        estat = estat.copy(estaSortejant = true, valorTriat = 0)
        viewModelScope.launch{
            delay(temps)
            estat = estat.copy(estaSortejant = false, valorTriat = (minim..maxim).random())
        }
    }
}
data class EstatTriaNumero(
    val valorTriat: Int = 0,
    val estaSortejant: Boolean = false
)