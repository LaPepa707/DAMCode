package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.filestore.firestoreManager
import com.montilivi.llistacompra.model.lineaLlista
import com.montilivi.llistacompra.model.report_autentification

class DAOLineaLlistaFirebaseImpl(firestoreManager: firestoreManager): DAOLineaLlista {
    val db: firestoreManager = firestoreManager
    override suspend fun obtenLinies(): report_autentification<List<lineaLlista>> {
        TODO("Not yet implemented")
    }

    override suspend fun afegeixLinea(linea: lineaLlista): report_autentification<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun eliminaLinea(linea: lineaLlista): report_autentification<Boolean> {
        TODO("Not yet implemented")
    }
}