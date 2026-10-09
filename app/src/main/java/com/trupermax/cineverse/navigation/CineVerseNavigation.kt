package com.trupermax.cineverse.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.trupermax.cineverse.navigation.Routes.FavoritesRoute
import com.trupermax.cineverse.navigation.Routes.HomeRoute
import com.trupermax.cineverse.navigation.Routes.MovieDetailsRoute
import com.trupermax.cineverse.navigation.Routes.SearchRoute
import com.trupermax.cineverse.ui.components.BottomNavItem
import com.trupermax.cineverse.ui.components.BottomNavigationBar
import com.trupermax.cineverse.ui.screens.FavoritesScreen
import com.trupermax.cineverse.ui.screens.HomeScreen
import com.trupermax.cineverse.ui.screens.MovieDetailScreen
import com.trupermax.cineverse.ui.screens.SearchScreen

@Composable
fun CineVerseNavigation() {
    val bottomNavItems = listOf(
        BottomNavItem(
            title = "Inicio",
            icon = Icons.Default.Home,
            route = HomeRoute
        ),
        BottomNavItem(
            title = "Buscar",
            icon = Icons.Default.Search,
            route = SearchRoute
        ),
        BottomNavItem(
            title = "Favoritos",
            icon = Icons.Default.Favorite,
            route = FavoritesRoute
        )
    )

    val backStack = rememberNavBackStack(
        HomeRoute
    )

    val currentRoute = backStack.last()

    val showBottomBar =
        currentRoute == HomeRoute ||
                currentRoute == SearchRoute ||
                currentRoute == FavoritesRoute

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(250)
                ) +
                        fadeIn(
                            animationSpec = tween(250)
                        ) +
                        expandVertically(
                            expandFrom = Alignment.Bottom,
                            animationSpec = tween(250)
                        ),

                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(250)
                ) +
                        fadeOut(
                            animationSpec = tween(200)
                        ) +
                        shrinkVertically(
                            shrinkTowards = Alignment.Bottom,
                            animationSpec = tween(250)
                        )
            ) {
                BottomNavigationBar(
                    items = bottomNavItems,
                    currentRoute = currentRoute,
                    onItemSelected = { route ->
                        if (currentRoute != route) {

                            while (backStack.size > 1) {
                                backStack.removeLastOrNull()
                            }

                            if (route != HomeRoute) {
                                backStack.add(route)
                            }
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = {
                backStack.removeLastOrNull()
            },
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth ->
                        fullWidth
                    },
                    animationSpec = tween(300)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { fullWidth ->
                        -fullWidth
                    },
                    animationSpec = tween(300)
                )
            },
            popTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth ->
                        -fullWidth
                    },
                    animationSpec = tween(300)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { fullWidth ->
                        fullWidth
                    },
                    animationSpec = tween(300)
                )
            },
            predictivePopTransitionSpec = { _ ->
                slideInHorizontally(
                    initialOffsetX = { fullWidth ->
                        -fullWidth
                    },
                    animationSpec = tween(300)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { fullWidth ->
                        fullWidth
                    },
                    animationSpec = tween(300)
                )
            },
            entryProvider = entryProvider {
                entry<HomeRoute> {
                    HomeScreen { movie ->
                        backStack.add(
                            MovieDetailsRoute(
                                movieId = movie.id
                            )
                        )
                    }
                }

                entry<SearchRoute> {
                    SearchScreen()
                }

                entry<FavoritesRoute> {
                    FavoritesScreen()
                }

                entry<MovieDetailsRoute> { route ->
                    MovieDetailScreen(
                        movieId = route.movieId
                    )
                }
            }
        )
    }
}