package com.montilivi.projecteautentificacio.ui.viewmodels

import android.R
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montilivi.llistacompra.dades.DAOFactory
import com.montilivi.llistacompra.model.llistaCompra
import com.montilivi.llistacompra.model.usuari
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class viewmodelLlistaCompra : ViewModel(){
    private var _estat = MutableStateFlow<LlistaCompraEstat>(LlistaCompraEstat())
    val estat : StateFlow<LlistaCompraEstat> = _estat.asStateFlow()
    val llistesRepositori = DAOFactory.obtenirDAOTasca(DAOFactory.TipusDB.FIREBASE)
    val usuariRepositori = DAOFactory.obtenirDAOUsuari(DAOFactory.TipusDB.FIREBASE)


    init {

    }

    fun afegeixLlista(llista: llistaCompra, user: usuari){
        viewModelScope.launch(Dispatchers.IO){
            llistesRepositori.creaLlistaCompra(llista, user)
        }
    }

    fun obtenirUsuari(correu: String){
        viewModelScope.launch(Dispatchers.IO){
            var usuariId = usuariRepositori.obtenirIdUsuariPerCorreu(correu)
            _estat.value = _estat.value.copy(usuariActualId = usuariId)
            var usuari = usuariRepositori.obtenirUsuariPerId(usuariId)
            _estat.value = _estat.value.copy(ususariActual = usuari)
            var llistes = usuariRepositori.obtenirLlistes(usuari)
            _estat.value = _estat.value.copy(llistes = llistes.toMutableList())
        }
    }

    fun obtenirNomUsuari(id: String): String{
        var nom = ""
        viewModelScope.launch(Dispatchers.IO){
            nom = llistesRepositori.obtenirUsuariNom(id)
        }
        return nom
    }

    fun modificaLlista(llista: llistaCompra){
        viewModelScope.launch(Dispatchers.IO){
            llistesRepositori.modificaLlistaCompra(llista)
        }
    }

    fun eliminaLlista(llista: llistaCompra, id: String){
        viewModelScope.launch(Dispatchers.IO){
            llistesRepositori.eliminaLlistaCompra(llista, id)
        }
    }

    fun treureUsuariDeLlista(llista: llistaCompra, id: String){
        viewModelScope.launch(Dispatchers.IO){
            llistesRepositori.eliminaLlistaCompra(llista, id)
        }
    }

    fun afegeixUsuariALlista(llista: llistaCompra, id: String){
        viewModelScope.launch(Dispatchers.IO){
            llistesRepositori.afegirUsuariALlista(llista, id)
        }
    }

    fun obtenLlistaComopraPerNom(nomLlista: String){
        viewModelScope.launch(Dispatchers.IO){
            llistesRepositori.obtenLlistaCompraPerNom(nomLlista)
        }
    }

    data class LlistaCompraEstat(
        val estaCarregant:Boolean = false,
        val llistes: MutableList<llistaCompra> = mutableListOf(),
        val esErroni:Boolean = false,
        val missatgeError:String = "",
        val usuariActualId : String = "",
        val ususariActual : usuari = usuari()
    )
}