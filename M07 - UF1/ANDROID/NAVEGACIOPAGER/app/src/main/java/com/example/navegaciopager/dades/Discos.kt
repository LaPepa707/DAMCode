package com.example.navegaciopager.dades
import com.example.navegaciopager.model.Disco
import kotlin.random.Random

object Discos {
    private val Nom = listOf(
        "Dulce",
        "Pequeño",
        "Demasiado",
        "Fiel",
        "Valiente",
        "Culto",
        "Suave",
        "Afortunado",
        "Querido",
        "Azul",
        "Rosa",
        "Negro",
        "Amor",
        "Guerra",
        "Infeliz",
        "Magnifico",
        "Deshonesto",
        "",
        "Mesa",
        "Silla",
        "Bosque",
        "Ciudad",
        "",
        "Pueblo",
        "Caballo",
        "El",
        "Ella",
        "Reina",
        "Rey",
        "Guerrero",
        "Sol",
        "Nieve",
        "Tormenta",
        "Blanco",
        "Negro",
        "Rio",
        "Ellos",
        "Caramelos"
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

    private fun InfoDisco(num : Int): Disco{
        return Disco(
            id = num,
            nom = "${Nom[Random.nextInt(0, Nom.size)]} ${Nom[Random.nextInt(0, Nom.size)]} ${Nom[Random.nextInt(0, Nom.size)]}",
            foto = "https://loremflickr.com/320/240/disc?lock=${num}",
            descripcio = " Fue popularizado en los 60s con la creación de las hojas \"Letraset\", las cuales contenian pasajes de Lorem Ipsum, " +
                    "y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.",
            paisOrigen = Pais[Random.nextInt(0, Pais.size)],
            artista = Artistes.obtenirArtiste().nom,
            enMercat = Random.nextInt(0, 100_000_000),
            venuts = Random.nextInt(0, 100_000_000)
        )
    }

    private val discos = (1..100).map { InfoDisco(it) }

    fun obtenDadesDelsDiscos() = discos

    fun obtenirDisc(): Disco {
        val disco = obtenDadesDelsDiscos()
        return disco.random()
    }
    fun obtenirDiscById(id: Int): Disco {
        val disc = obtenDadesDelsDiscos()
        val discEncontrado = disc.find { it.id == id }
        return discEncontrado ?: obtenirDisc()
    }

}