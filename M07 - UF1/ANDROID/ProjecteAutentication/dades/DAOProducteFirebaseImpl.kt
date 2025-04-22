package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.filestore.firestoreManager
import com.montilivi.llistacompra.model.producte
import com.montilivi.llistacompra.model.report_autentification
import kotlinx.coroutines.tasks.await

class DAOProducteFirebaseImpl(firestoreManager: firestoreManager): DAOProducte {
    val db: firestoreManager = firestoreManager
    override suspend fun obtenProductes(): report_autentification<List<producte>> {
        val llistaProductes = mutableListOf<producte>()
        return try {
            val refProductes = db.firestoreDB.collection(db.PRODUCTE)
            val productesSnapshot = refProductes.get().await()
            for (document in productesSnapshot.documents) {
                val producte = document.toObject(producte::class.java)
                if (producte != null) {
                    llistaProductes.add(producte)
                }
            }
            report_autentification.Exit(llistaProductes)
        } catch (e: Exception) {
            report_autentification.Fracas(e.message ?: "Error cercant productes")
        }
    }
}