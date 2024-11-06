package com.example.dadesinavegacio.Dades

import com.example.dadesinavegacio.Model.Artista
import kotlin.random.Random

object Artistes {
    private val Noms = listOf(
        "LE SEERAFIM",
        "NEW JEANS",
        "XG",
        "BTS",
        "LISA",
        "Emilia",
        "Rammstein",
        "AC/DC",
        "Red Hot Chili Peppers",
        "Willie Colón",
        "Sabrina Carpenter",
        "a-ha",
        "Earth, Wind & Fire",
        "RM",
        "G-Eazy",
        "Perfume",
        "Rosalía",
        "Daddy Yankee",
        "Whitney Houston",
        "Trueno",
        "Milo J",
        "Justin Timberlake",
        "Avril Lavigne",
        "Eminem",
        "Rihana",
        "Travis Scott",
        "Quevedo",
        "Gorillaz",
        "Lady Gaga"
    )

    private val LletresFinals = listOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z"
    )

    private val Pais = listOf(
        "BG",
        "ES",
        "AU",
        "IE",
        "KR",
        "JP",
        "PR",
        "NL",
        "US",
        "MX",
        "AR",
        "GB",
        "CAN",
        "BE",
        "TH"
    )

    private fun InfoArtistes(num : Int): Artista
    {
        val posicioPais = Random.nextInt(0, Pais.size)
        val posicioNom = Random.nextInt(0, Noms.size)
        val posicioLletres = Random.nextInt(0, LletresFinals.size)
        return Artista(
            id = num,
            nom = "${Noms[posicioNom]} ${LletresFinals[posicioLletres]}",
            foto = "https://loremflickr.com/320/240/warrior?lock=${num}",
            descripcio = " Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, " +
                    "y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
            premisNacionals = Random.nextInt(101),
            premisInternbacionals = Random.nextInt(101),
            paisOrigen = Pais[posicioPais],
            integrants = Random.nextInt(1, 7),
            numeroAlbums = Random.nextInt(100),
            numeroCansons = Random.nextInt(300)
        )
    }

    public fun obtenDadesDelsArtistes() =
        (1 ..100)
            .toList()
            .map { InfoArtistes(it) }

    public fun obtenirArtiste(): Artista {
        val artistes = obtenDadesDelsArtistes()
        return artistes.random()
    }

}