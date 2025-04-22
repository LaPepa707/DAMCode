package com.montilivi.llistacompra.model

class categoria (
    var id:String ="",
    val nom:String = "",
    val colorFons:String = "",
    val colorText:String = "",
    val productes:MutableList<String> = mutableListOf()
)