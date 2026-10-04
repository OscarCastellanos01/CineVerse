package com.trupermax.cineverse.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Routes: NavKey {
    @Serializable
    data object HomeRoute : Routes()

    @Serializable
    data object SearchRoute : Routes()

    @Serializable
    data object FavoritesRoute : Routes()

    @Serializable
    data class MovieDetailsRoute(
        val movieId: Int
    ) : Routes()
}
