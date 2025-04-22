package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.model.categoria
import com.montilivi.llistacompra.model.report_autentification
import kotlinx.coroutines.flow.Flow

interface DAOCategoria {
    suspend fun obtenCategories(): Flow<report_autentification<List<categoria>>>
    suspend fun afegeixCategoria(categoria: categoria): report_autentification<Boolean>
    suspend fun eliminaCategoria(id: String): report_autentification<Boolean>
    suspend fun actualitzaCategoria(categoria: categoria): report_autentification<Boolean>
    suspend fun existeixCategoria(nom: String): report_autentification<Boolean>
    suspend fun obtenCategoria(id:String): report_autentification<categoria>
}