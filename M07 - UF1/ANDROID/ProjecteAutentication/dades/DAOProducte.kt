package com.montilivi.llistacompra.dades

import com.montilivi.llistacompra.model.producte
import com.montilivi.llistacompra.model.report_autentification

interface DAOProducte {
    suspend fun obtenProductes(): report_autentification<List<producte>>
}