package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.model.categoria
import com.montilivi.llistacompra.model.llistaCompra
import com.montilivi.llistacompra.model.report_autentification
import com.montilivi.llistacompra.model.usuari
import kotlinx.coroutines.flow.Flow

interface DAOUsuari {
    suspend fun obtenUsuaris(): Flow<report_autentification<MutableList<usuari>>>
    suspend fun afegeixUsuari(usuari: usuari): report_autentification<Boolean>
    suspend fun existeixUsuari(correu: String): report_autentification<Boolean>
    suspend fun obtenirIdUsuariPerCorreu(correu: String): String
    suspend fun obtenirUsuariPerId(correu: String): usuari
    suspend fun afegirLlistaCompraAUsuari(correu: String, id : String): report_autentification<Boolean>
    suspend fun afegirUsuariHabitualAUsuari(correu: String, id: String): report_autentification<Boolean>
    suspend fun obtenirLlistes(usuari: usuari): List<llistaCompra>
}