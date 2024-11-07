package com.example.dadesinavegacio.Dades

import com.example.dadesinavegacio.Dades.Artistes.obtenDadesDelsArtistes
import com.example.dadesinavegacio.Dades.Discos.obtenDadesDelsDiscos
import com.example.dadesinavegacio.Dades.Discos.obtenirDisc
import com.example.dadesinavegacio.Model.Artista
import com.example.dadesinavegacio.Model.Consert
import com.example.dadesinavegacio.Model.Disco
import kotlin.random.Random

object Conserts {
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

    private fun InfoConsert(num : Int): Consert {
        return Consert(
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
    public fun obtenDadesDelsConserts() =
        (1 ..100)
            .toList()
            .map { InfoConsert(it) }

    public fun obtenirConsert(): Consert {
        val consert = obtenDadesDelsConserts()
        return consert.random()
    }
    public fun obtenirConsertById(id: Int): Consert {
        val cons = obtenDadesDelsDiscos()
        val consEncontrado = cons.find { it.id == id }
        return (consEncontrado ?: obtenirConsert()) as Consert
    }
}