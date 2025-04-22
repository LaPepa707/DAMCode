package com.montilivi.llistacompra.model

sealed class report_autentification<out T>{
    data class Exit<T>(val dades: T): report_autentification<T>()
    data class Fracas(val msgError: String): report_autentification<Nothing>()
}