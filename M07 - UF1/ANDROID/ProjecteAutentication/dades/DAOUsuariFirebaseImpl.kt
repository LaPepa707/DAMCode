package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.filestore.firestoreManager
import com.montilivi.llistacompra.model.llistaCompra
import com.montilivi.llistacompra.model.report_autentification
import com.montilivi.llistacompra.model.usuari
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class DAOUsuariFirebaseImpl(firestoreManager: firestoreManager): DAOUsuari {
    val db: firestoreManager = firestoreManager
    override suspend fun obtenUsuaris(): Flow<report_autentification<MutableList<usuari>>> = callbackFlow {
        try{
            val refUsuaru = db.firestoreDB.collection(db.CATEGORIA)

            val subscripcio = refUsuaru.addSnapshotListener{
                    snapshot, _ ->
                snapshot?.let { querySnapshot ->  //Si l'snapshot no es null, processem la llista de documents
                    val usuaris = mutableListOf<usuari>()
                    for (document in querySnapshot.documents) {
                        val usuari = document.toObject(usuari::class.java)
                        usuari?.let { usuaris.add(it) }
                    }

                    trySend(report_autentification.Exit(usuaris)).isSuccess
                }
            }
            awaitClose { subscripcio.remove() }
        }catch (e: Exception) {
            trySend (report_autentification.Fracas(e.message ?: "Error obtenint llista d'estats"))
        }
    }

    override suspend fun afegeixUsuari(usuari: usuari): report_autentification<Boolean> {
        var existeix = false
        try{
            runBlocking {
                val resposta = existeixUsuari(usuari.correu)
                if (resposta is report_autentification.Exit)
                    existeix = resposta.dades
                else if (resposta is report_autentification.Fracas)
                    throw Exception(resposta.msgError)
            }
            if (!existeix) {
                val refUsuaris = db.firestoreDB.collection(db.USUARI)
                val refUsuariNou = refUsuaris.document()
                usuari.id = refUsuariNou.id
                refUsuariNou.set(usuari)
                existeix = true
            }
        } catch (e: Exception) {
            return report_autentification.Fracas(e.message ?:
            "Error en alta d'usuari ${usuari.correu}")
        }
        return report_autentification.Exit(existeix)
    }

    override suspend fun existeixUsuari(correu: String): report_autentification<Boolean> {
        var consultaBuida = false
        try {
            val refUsuaris = db.firestoreDB.collection(db.USUARI)
            val consulta = refUsuaris.whereEqualTo("correu", correu)
            consultaBuida = consulta.get().await().documents.isEmpty()
        }catch (e: Exception) {
            report_autentification.Fracas(e.message ?: "Error cercant l'usuari $correu" )
        }
        return report_autentification.Exit(!consultaBuida)
    }

    override suspend fun obtenirIdUsuariPerCorreu(correu: String): String {
        var idUsuari = ""
        try {
            val refUsuaris = db.firestoreDB.collection(db.USUARI)
            val query = refUsuaris.whereEqualTo("correu", correu)
            idUsuari = query.get().await().documents[0].id
            val resultat = idUsuari.toString()

        } catch (e: Exception) {
            throw Exception(e.message ?: "Error cercant l'usuari $correu")
        }
        return idUsuari
    }

    override suspend fun obtenirUsuariPerId(id: String): usuari {
        var usuari = usuari()
        try {
            val refUsuaris = db.firestoreDB.collection(db.USUARI)
            val refUsuari = refUsuaris.document(id)
            val documentSnapshot = refUsuari.get().await()
            if (documentSnapshot.exists()) {
                usuari = documentSnapshot.toObject(usuari::class.java) ?: usuari()
            }
        } catch (e: Exception) {
            throw Exception(e.message ?: "Error cercant l'usuari amb id $id")
        }
        return usuari
    }

    override suspend fun afegirLlistaCompraAUsuari(
        correu: String,
        id: String
    ): report_autentification<Boolean> {
        return try {
            val usuariId = obtenirIdUsuariPerCorreu(correu)
            val usuari = obtenirUsuariPerId(usuariId)
            val refusuari = db.firestoreDB.collection(db.USUARI).document(correu)
            usuari.llistaCompra.add(id)
            refusuari.set(usuari).await()
            report_autentification.Exit(true)
        }
        catch (e: Exception){
            return report_autentification.Fracas(e.message ?: "Error desconegut")
        }
    }

    override suspend fun afegirUsuariHabitualAUsuari(
        correu: String,
        id: String
    ): report_autentification<Boolean> {
        return try {
            val usuariId = obtenirIdUsuariPerCorreu(correu)
            val usuari = obtenirUsuariPerId(usuariId)
            val refusuari = db.firestoreDB.collection(db.USUARI).document()
            usuari.usuarisHabituals.add(id)
            refusuari.set(usuari).await()
            report_autentification.Exit(true)
        }
        catch (e: Exception){
            return report_autentification.Fracas(e.message ?: "Error desconegut")
        }
    }

    override suspend fun obtenirLlistes(usuari: usuari): List<llistaCompra> {
        var llistes = mutableListOf<llistaCompra>()
        try{
            val idLlistes = usuari.llistaCompra
            for (id in idLlistes){
                val refLlistes = db.firestoreDB.collection(db.LLISTACOMPRA)
                val refLlista = refLlistes.document(id)
                val doc = refLlista.get().await()
                val llistaObtinguda = doc.toObject(llistaCompra::class.java)
                llistaObtinguda?.let { llistes.add(it) }
            }
        }catch (e: Exception) {
            throw Exception(e.message ?: "Error obtenint llista d'estats")
        }
        return llistes
    }

}