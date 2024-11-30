package cat.institutmontivi.decissorviewmodel.dades

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class PreferenciesDataStore(private val context : Context) {
    companion object{
        private val Context.dataStore by preferencesDataStore("preferencies")
        private val TEMPS_CARA_O_CREU = longPreferencesKey("tempsCaraOCreu")
        private val TEMPS_TRIA_NUMERO = longPreferencesKey("tempsTriaNumero")
        private val MINIM_TRIA_NUMERO = intPreferencesKey("minimTriaNumero")
        private val MAXIM_TRIA_NUMERO = intPreferencesKey("maximTriaNumero")
    }
    val getTeampsCaraOCreu = context.dataStore.data.map { preferencies ->
        preferencies[TEMPS_CARA_O_CREU] ?: 1000
    }
    suspend fun setTempsCaraOCreu(temps: Long){
        context.dataStore.edit { preferencies ->
            preferencies[TEMPS_CARA_O_CREU] = temps
        }
    }
    val getTeampsTriaNumero = context.dataStore.data.map { preferencies ->
        preferencies[TEMPS_TRIA_NUMERO] ?: 1000
    }
    suspend fun setTeampsTriaNumero(temps: Long){
        context.dataStore.edit { preferencies ->
            preferencies[TEMPS_TRIA_NUMERO] = temps
        }
    }
    val getMinimTriaNumero = context.dataStore.data.map { preferencies ->
        preferencies[MINIM_TRIA_NUMERO] ?: 0
    }
    suspend fun setMinimTriaNumero(minim: Int){
        context.dataStore.edit { preferencies ->
            preferencies[MINIM_TRIA_NUMERO] = minim
        }
    }
    val getMaximTriaNumero = context.dataStore.data.map { preferencies ->
        preferencies[MAXIM_TRIA_NUMERO] ?: 1000
    }
    suspend fun setMaximTriaNumero(maxim: Int){
        context.dataStore.edit { preferencies ->
            preferencies[MAXIM_TRIA_NUMERO] = maxim
        }
    }
}