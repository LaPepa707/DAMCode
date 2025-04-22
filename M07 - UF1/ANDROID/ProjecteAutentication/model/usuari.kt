package com.montilivi.llistacompra.model

class usuari(
    var id:String = "",
    val nom:String = "",
    val cognoms: String = "",
    val fotoId:String = "",//url de la foto
    val correu:String = "",
    var llistaCompra: MutableList<String> = mutableListOf(),
    var usuarisHabituals: MutableList<String> = mutableListOf(),
)