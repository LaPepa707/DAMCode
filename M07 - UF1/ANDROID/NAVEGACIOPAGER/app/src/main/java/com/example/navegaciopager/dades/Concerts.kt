package com.example.navegaciopager.dades

import com.example.navegaciopager.model.Concert
import kotlin.random.Random

object Concerts {
    private val Nom = listOf(
        "LA",
        "GIRA",
        "MUNDO",
        "ROSA",
        "LEYENDA",
        "HISTORIA",
        "NEGRO",
        "BRILLO",
        "",
        "UNIVERSO",
        "GALAXIA",
        "EL",
        "NUESTRO",
        "",
        "ERA",
        "COLORINES",
        "BLANCO"
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

    private fun InfoConcert(num : Int): Concert {
        return Concert(
            id = num,
            nom = "${Nom[Random.nextInt(0, Nom.size)]} ${Nom[Random.nextInt(0, Nom.size)]}",
            foto = "https://loremflickr.com/320/240/concert?lock=${num}",
            descripcio =  " Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, " +
                    "y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
            artista = Artistes.obtenirArtiste().nom,
            pais = Pais[Random.nextInt(0, Pais.size)],
            capacitat = Random.nextInt(0, 500_000),
            numeroEntradesComprades = Random.nextInt(0, 500_000)
        )
    }
    private val concerts = (1..100).map { InfoConcert(it) }

    fun obtenDadesDelsConcerts() = concerts

    fun obtenirConcert(): Concert {
        val consert = obtenDadesDelsConcerts()
        return consert.random()
    }

    fun obtenirConsertById(id: Int): Concert {
        val cons = obtenDadesDelsConcerts()
        val consEncontrado = cons.find { it.id == id }
        return consEncontrado ?: obtenirConcert()
    }

}