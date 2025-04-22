package com.montilivi.llistacompra.dades

import android.R
import com.montilivi.llistacompra.filestore.firestoreManager
import com.montilivi.llistacompra.model.categoria
import com.montilivi.llistacompra.model.report_autentification
import com.montilivi.llistacompra.model.llistaCompra
import com.montilivi.llistacompra.model.usuari
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlin.text.get
import kotlin.text.set

class DAOLlistaCompraFirebaseImpl(firestoreManager: firestoreManager): DAOLlistaCompra {
    val db: firestoreManager = firestoreManager
    override suspend fun creaLlistaCompra(llista: llistaCompra, user: usuari): report_autentification<Boolean> {
        var existeix = false
        try {
            val resposta = existeixLlistaCompra(llista.nom)
            if (resposta is report_autentification.Exit) {
                existeix = resposta.dades
            } else if (resposta is report_autentification.Fracas) {
                throw Exception(resposta.msgError)
            }

            if (!existeix) {
                val refLlistes = db.firestoreDB.collection(db.LLISTACOMPRA)
                val refLlistaNou = refLlistes.document()
                val refUser = db.firestoreDB.collection(db.USUARI).document(user.id)
                llista.id = refLlistaNou.id
                llista.usuarisAutoritzats.add(user.id)
                refLlistaNou.set(llista).await()
                user.llistaCompra.add(llista.id)
                refUser.set(user).await()
                existeix = true
            }
        } catch (e: Exception) {
            return report_autentification.Fracas(e.message ?: "Error en alta llista ${llista.nom}")
        }
        return report_autentification.Exit(existeix)
    }

    override suspend fun modificaLlistaCompra(llista: llistaCompra): report_autentification<Boolean> {
        var existeix = false
        try {
            val refLlistes = db.firestoreDB.collection(db.LLISTACOMPRA)
            val refLlistaNou = refLlistes.document()
            refLlistaNou.set(llista)
            existeix = true
        }
        catch (e: Exception) {
            return report_autentification.Fracas(e.message ?:
            "Error en modificar llista ${llista.nom}")
        }
        return report_autentification.Exit(existeix)
    }

    override suspend fun eliminaLlistaCompra(llista: llistaCompra, id: String): report_autentification<Boolean> {
        var tret = false
        try {
            val refLlista = db.firestoreDB.collection(db.LLISTACOMPRA).document(llista.id)
            val refUsuari = db.firestoreDB.collection(db.USUARI).document(id)
            val usuariSnapShot = refUsuari.get().await()
            val usuari = usuariSnapShot.toObject(usuari::class.java)
            usuari?.llistaCompra?.remove(llista.id)
            usuari?.let { refUsuari.set(it) }
            llista.usuarisAutoritzats.remove(id)
            refLlista.set(llista)
            if(llista.usuarisAutoritzats.size == 0){
                refLlista.delete().await()
            }
            tret = true
        } catch (e: Exception){
            report_autentification.Fracas(e.message ?: "Error en eliminar l'ususari de la llista" )
        }
        return report_autentification.Exit(tret)
    }

    override suspend fun afegirUsuariALlista(
        llista: llistaCompra,
        id: String//CORREU
    ): report_autentification<Boolean> {
        var afegit = false
        try {
            val refLlista = db.firestoreDB.collection(db.LLISTACOMPRA).document(llista.id)
            val refUsuari = db.firestoreDB.collection(db.USUARI)
            val query = refUsuari.whereEqualTo("correu", id)
            val idUsuari = query.get().await().documents[0].id
            val usuariSnapShot = refUsuari.document(idUsuari)
            val doc = usuariSnapShot.get().await()
            val usuari = doc.toObject(usuari::class.java)
            usuari?.llistaCompra?.add(llista.id)
            usuari?.let { usuariSnapShot.set(it) }
            llista.usuarisAutoritzats.add(idUsuari)
            refLlista.set(llista)
            afegit = true
        }
        catch (e: Exception){
            report_autentification.Fracas(e.message ?: "Error en afegir l'usuari a la llista" )
        }
        return report_autentification.Exit(afegit)
    }

    override suspend fun existeixLlistaCompra(nom: String): report_autentification<Boolean> {
        var consultaBuida = false
        try {
            val refLlista = db.firestoreDB.collection(db.LLISTACOMPRA)
            val consulta = refLlista.whereEqualTo("nom", nom)
            consultaBuida = consulta.get().await().documents.isEmpty()
        }catch (e: Exception) {
            report_autentification.Fracas(e.message ?: "Error cercant la llista amb nom $nom" )
        }
        return report_autentification.Exit(!consultaBuida)
    }

    override suspend fun obtenLlistesCompra(id: String): List<llistaCompra>{
        var llista = mutableListOf<llistaCompra>()
        try{
            val refUsuaris = db.firestoreDB.collection(db.USUARI)
            val refUsuari = refUsuaris.document(id)
            val doc = refUsuari.get().await()
            val usuari = doc.toObject(usuari::class.java)?: usuari()
            val idLlistes = usuari.llistaCompra
            for (id in idLlistes){
                val refLlistes = db.firestoreDB.collection(db.LLISTACOMPRA)
                val refLlista = refLlistes.document(id)
                val doc = refLlista.get().await()
                val llistaObtinguda = doc.toObject(llistaCompra::class.java)
                llistaObtinguda?.let { llista.add(it) }
                report_autentification.Exit(llista)
            }
        }catch (e: Exception) {
            report_autentification.Fracas(e.message ?: "Error obtenint llista d'estats")
        }
        return llista?: mutableListOf()
    }

    override suspend fun obtenirUsuariNom(id: String): String {
        var nom = ""
        try {
            val refUsuari = db.firestoreDB.collection(db.USUARI).document(id)
            val doc = refUsuari.get().await()
            val usuari = doc.toObject(usuari::class.java)
            nom = usuari?.nom ?: ""
        } catch (e: Exception) {
            throw Exception(e.message ?: "Error cercant l'usuari $id")
        }
        return nom
    }

    override suspend fun obtenLlistaCompraPerNom(nom: String): llistaCompra {
        var llista = llistaCompra()
        try {
            val refLlistes = db.firestoreDB.collection(db.LLISTACOMPRA)
            val consulta = refLlistes.whereEqualTo("nom", nom)
            val doc = consulta.get().await().documents[0]
            llista = doc.toObject(llistaCompra::class.java) ?: llistaCompra()
        } catch (e: Exception) {
            throw Exception(e.message ?: "Error cercant la llista $nom")
        }
        return llista
    }


}

