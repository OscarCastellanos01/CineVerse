package com.trupermax.cineverse.data

import com.trupermax.cineverse.R
import com.trupermax.cineverse.model.Movie

val sampleMovies = listOf(
    Movie(
        id = 1,
        title = "Interstellar",
        releaseYear = 2014,
        overview = "Un grupo de exploradores emprende un viaje espacial en busca de un nuevo hogar para la humanidad.",
        posterResId = R.drawable.interstellar,
        genres = listOf(
            "Ciencia ficción",
            "Aventura",
            "Drama"
        )
    ),
    Movie(
        id = 2,
        title = "Inception",
        releaseYear = 2010,
        overview = "Un especialista en infiltrarse en los sueños recibe la misión de implantar una idea en la mente de una persona.",
        posterResId = R.drawable.poster_inception,
        genres = listOf(
            "Ciencia ficción",
            "Acción",
            "Suspenso"
        )
    ),
    Movie(
        id = 3,
        title = "The Dark Knight",
        releaseYear = 2008,
        overview = "Batman enfrenta a un criminal que amenaza con sumir a Ciudad Gótica en el caos.",
        posterResId = R.drawable.poster_the_dark_knight,
        genres = listOf(
            "Acción",
            "Crimen",
            "Drama"
        )
    )
)