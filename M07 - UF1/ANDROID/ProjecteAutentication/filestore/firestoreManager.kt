package com.montilivi.llistacompra.filestore

import com.google.firebase.firestore.FirebaseFirestore

class firestoreManager {
    val CATEGORIA = "Categoria"
    val USUARI = "Usuari"
    val LLISTACOMPRA = "LlistaCompra"
    val PRODUCTE = "Producte"
    val LINEALLISTA = "LineaLlista"
    public val firestoreDB : FirebaseFirestore = FirebaseFirestore.getInstance()
}