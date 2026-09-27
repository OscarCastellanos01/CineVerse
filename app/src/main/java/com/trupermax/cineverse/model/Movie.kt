package com.trupermax.cineverse.model

data class Movie(
    val id: Int,
    val title: String,
    val releaseYear: Int,
    val overview: String,
    val posterResId: Int,
    val genres: List<String>
)
