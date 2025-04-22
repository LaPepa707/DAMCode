package com.montilivi.llistacompra.dades

import android.R
import com.montilivi.llistacompra.model.categoria
import com.montilivi.llistacompra.model.report_autentification
import com.montilivi.llistacompra.model.llistaCompra
import com.montilivi.llistacompra.model.usuari
import kotlinx.coroutines.flow.Flow

interface DAOLlistaCompra {
    suspend fun creaLlistaCompra(llista: llistaCompra, user: usuari): report_autentification<Boolean>
    suspend fun modificaLlistaCompra(llista: llistaCompra): report_autentification<Boolean>
    suspend fun eliminaLlistaCompra(llista: llistaCompra, id : String): report_autentification<Boolean>
    suspend fun afegirUsuariALlista(llista: llistaCompra, id: String): report_autentification<Boolean>
    suspend fun existeixLlistaCompra(nom: String) : report_autentification<Boolean>
    suspend fun obtenLlistesCompra(id: String): List<llistaCompra>
    suspend fun obtenirUsuariNom(id: String): String
    suspend fun obtenLlistaCompraPerNom(nom: String): llistaCompra
}