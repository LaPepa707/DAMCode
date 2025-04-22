package com.montilivi.llistacompra.model

class llistaCompra (
    var id:String = "",
    val nom:String = "",
    val usuarisAutoritzats:MutableList<String> = mutableListOf(),
    val lineasList:MutableList<String> = mutableListOf()
)