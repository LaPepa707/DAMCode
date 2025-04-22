package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.model.lineaLlista
import com.montilivi.llistacompra.model.report_autentification

interface DAOLineaLlista {
    suspend fun obtenLinies(): report_autentification<List<lineaLlista>>
    suspend fun afegeixLinea(linea: lineaLlista): report_autentification<Boolean>
    suspend fun eliminaLinea(linea: lineaLlista): report_autentification<Boolean>
}