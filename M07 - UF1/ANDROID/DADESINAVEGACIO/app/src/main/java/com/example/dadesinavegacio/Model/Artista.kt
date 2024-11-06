package com.example.dadesinavegacio.Model

data class Artista (
    val id: Int,
    val nom: String,
    val foto: String,
    val descripcio: String,
    val premisNacionals: Int,
    val premisInternbacionals: Int,
    val paisOrigen: String,
    val integrants: Int,
    val numeroAlbums: Int,
    val numeroCansons: Int
)