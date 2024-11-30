package cat.institutmontivi.decissorviewmodel.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelCaraOCreu: ViewModel() {
    var estat by mutableStateOf(EstatCaraOCreu())
        private set
    init {
        estat = estat.copy(
            estatSortejant = false,
            resultat = 0
        )
    }

    fun sorteja(i: Long) {
        estat = estat.copy(
            estatSortejant = true,
            resultat = 0
        )
        viewModelScope.launch{
            delay(i)
            estat = estat.copy(
                estatSortejant = false,
                resultat = (1..2).random()
            )
        }

    }

}
data class EstatCaraOCreu(
    val estatSortejant : Boolean = false,
    val resultat: Int = 0
)

sealed class EventCaraOCreu(
    val estatSortejant: Boolean = false,
    val resultat: Int = 0
)