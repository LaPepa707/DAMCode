package com.montilivi.projecteautentificacio.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montilivi.llistacompra.dades.DAOFactory
import com.montilivi.llistacompra.model.report_autentification
import com.montilivi.llistacompra.model.usuari
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class viewmodelUsuari: ViewModel() {
    private var _estat = MutableStateFlow<UsuariEstat>(UsuariEstat())
    val estat : StateFlow<UsuariEstat> = _estat.asStateFlow()
    val usuariRepositori = DAOFactory.obtenirDAOUsuari(DAOFactory.TipusDB.FIREBASE)

    fun afegeixUsuari(usuari: usuari){
        viewModelScope.launch(Dispatchers.IO){
            usuariRepositori.afegeixUsuari(usuari)
        }
    }
    data class UsuariEstat(
        val estaCarregant: Boolean = false,
        val esErroni: Boolean = false,
        var missatgeError: String = ""
    )
}