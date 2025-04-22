package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.filestore.firestoreManager
import com.montilivi.llistacompra.model.categoria
import com.montilivi.llistacompra.model.report_autentification
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class DAOCategoriaFirebaseImpl(firestoreManager: firestoreManager): DAOCategoria {
    val db: firestoreManager = firestoreManager
    override suspend fun obtenCategories(): Flow<report_autentification<List<categoria>>> = callbackFlow {
        var llista = mutableListOf<categoria>()
        try{
            val refCategoria = db.firestoreDB.collection(db.CATEGORIA)

            val subscripcio = refCategoria.addSnapshotListener{
                    snapshot, _ ->
                snapshot?.let { querySnapshot ->  //Si l'snapshot no es null, processem la llista de documents
                    val categories = mutableListOf<categoria>()
                    for (document in querySnapshot.documents) {
                        val categoria = document.toObject(categoria::class.java)
                        categoria?.let { categories.add(it) }
                    }

                    trySend(report_autentification.Exit(categories)).isSuccess
                }
            }
            awaitClose { subscripcio.remove() }
        }catch (e: Exception) {
                trySend (report_autentification.Fracas(e.message ?: "Error obtenint llista d'estats"))
        }
    }

    override suspend fun afegeixCategoria(categoria: categoria): report_autentification<Boolean> {
        var existeix = false
        try {
            runBlocking {//cal posar el runblocking per fer chaining i que bloquegi fins que no acabi la crida
                val resposta = existeixCategoria(categoria.nom)
                if (resposta is report_autentification.Exit)
                    existeix = resposta.dades
                else if (resposta is report_autentification.Fracas)
                    throw Exception(resposta.msgError)

                if (!existeix) {
                    val refEstats = db.firestoreDB.collection(db.CATEGORIA)
                    val refEstatNou = refEstats.document()
                    categoria.id = refEstatNou.id
                    refEstatNou.set(categoria)
                    existeix = true
                }
            }
        }catch (e: Exception) {
            return report_autentification.Fracas( "Error afegint un estat nou (${categoria.nom}) : ${e.message}")
        }
        return report_autentification.Exit(existeix)
    }

    override suspend fun eliminaCategoria(id: String): report_autentification<Boolean> {
        var eliminat = false
        try {
            val refCategories = db.firestoreDB.collection(db.CATEGORIA)
            val refCategoria = refCategories.document(id)
            refCategoria.delete().await()
            eliminat = true
        }catch (e: Exception) {
            return report_autentification.Fracas(e.message?:"Error eliminant l'estat ${id}")
        }
        return report_autentification.Exit(eliminat)
    }

    override suspend fun actualitzaCategoria(categoria: categoria): report_autentification<Boolean> {
        var actualitzat = false
        try {
            val refEstats = db.firestoreDB.collection(db.CATEGORIA)
            val refEstat = refEstats.document(categoria.id)
            refEstat.set(categoria).await()
            actualitzat = true
        }catch (e: Exception) {
            return report_autentification.Fracas(e.message?:"Error cercant l'estat ${categoria.nom}")
        }
        return report_autentification.Exit(actualitzat)
    }

    override suspend fun existeixCategoria(nom: String): report_autentification<Boolean> {
        var noHiEs = false
        try {
            val refEstats = db.firestoreDB.collection(db.CATEGORIA)
            val consulta = refEstats.whereEqualTo("nom", nom)
            noHiEs = consulta.get().await().documents.isEmpty()
        }catch (e: Exception) {
            return report_autentification.Fracas(e.message?:"Error cercant l'estat $nom")
        }
        return report_autentification.Exit(!noHiEs)
    }

    override suspend fun obtenCategoria(id: String): report_autentification<categoria> {
        var categoria = categoria()
        try {
            val refCategories = db.firestoreDB.collection(db.CATEGORIA)
            val refCategoria = refCategories.document(id)
            val document = refCategoria.get().await()
            categoria = document.toObject(categoria::class.java)?: categoria()
        }catch (e: Exception) {
            return report_autentification.Fracas(e.message?:"Error cercant l'estat $id")
        }
        return report_autentification.Exit(categoria)
    }

}