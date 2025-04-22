package com.montilivi.llistacompra.model

class lineaLlista (
    val id: String,
    val idProducte:String,
    val quantitat: Int,
    val comprat: Boolean?,
    val observacions: String,
    val usuariComprador : String,
    val imatges : List<String> = listOf(),
    val audios: List<String> = listOf(),
    val videos: List<String> = listOf()
)