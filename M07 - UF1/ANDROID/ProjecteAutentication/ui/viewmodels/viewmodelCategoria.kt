package com.montilivi.projecteautentificacio.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montilivi.llistacompra.dades.DAOFactory
import com.montilivi.llistacompra.model.categoria
import com.montilivi.llistacompra.model.report_autentification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class viewmodelCategoria: ViewModel(){
    private var _estat = MutableStateFlow<CategoriesEstat>(CategoriesEstat())
    val estat: StateFlow<CategoriesEstat> = _estat.asStateFlow()
    val categoriesRepositori = DAOFactory.obtenirDAOCategoria( DAOFactory.TipusDB.FIREBASE)

    init{
        obtenCategories()
    }

    fun afegeixCategoria(categoria: categoria){
        viewModelScope.launch(Dispatchers.IO){
            categoriesRepositori.afegeixCategoria(categoria)
        }
    }

    fun obtenCategories(){
        viewModelScope.launch(Dispatchers.IO){
            _estat.value = _estat.value.copy(estaCarregant = true)
            categoriesRepositori.obtenCategories().collect{ resposta ->
                if(resposta is report_autentification.Exit){
                    val dades = resposta.dades
                    _estat.emit(
                        estat.value.copy(
                            estaCarregant = false,
                            categories = dades,
                            esErroni = false,
                            missatgeError = ""
                        )
                    )
                }
                else if(resposta is report_autentification.Fracas){
                    _estat.emit(
                        estat.value.copy(
                            estaCarregant = false,
                            categories = listOf(),
                            esErroni = true,
                            missatgeError = resposta.msgError
                        )
                    )
                }
            }
        }

    }

    fun eliminaCategoria(id:String){
        viewModelScope.launch(Dispatchers.IO){
            categoriesRepositori.eliminaCategoria(id)
        }
    }

    fun actualitzaCategoria(categoria: categoria){
        viewModelScope.launch(Dispatchers.IO){
            categoriesRepositori.actualitzaCategoria(categoria)
        }
    }

    data class CategoriesEstat(
        val estaCarregant: Boolean= true,
        val categories: List<categoria> = listOf(),
        val esErroni: Boolean = false,
        val missatgeError: String = ""
    )
}