package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.filestore.firestoreManager

object DAOFactory {
    enum class TipusDB{
        FIREBASE
    }
    fun obtenirDAOCategoria(tipusDB: TipusDB): DAOCategoria{
        return when(tipusDB){
            TipusDB.FIREBASE -> DAOCategoriaFirebaseImpl(firestoreManager())
        }
    }
    fun obtenirDAOTasca(tipusDB: TipusDB): DAOLlistaCompra{
        return when(tipusDB){
            TipusDB.FIREBASE -> DAOLlistaCompraFirebaseImpl(firestoreManager())
        }
    }
    fun obtenirDAOUsuari(tipusDB: TipusDB): DAOUsuari{
        return when(tipusDB){
            TipusDB.FIREBASE -> DAOUsuariFirebaseImpl(firestoreManager())
        }
    }
    fun obtenirDAOLineaLlista(tipusDB: TipusDB): DAOLineaLlista{
        return when(tipusDB){
            TipusDB.FIREBASE -> DAOLineaLlistaFirebaseImpl(firestoreManager())
        }
    }
    fun obtenirDAOProducte(tipusDB: TipusDB): DAOProducte{
        return when(tipusDB){
            TipusDB.FIREBASE -> DAOProducteFirebaseImpl(firestoreManager())
        }
    }
}