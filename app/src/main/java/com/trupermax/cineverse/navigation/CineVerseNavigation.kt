package com.trupermax.cineverse.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.trupermax.cineverse.ui.screens.FavoritesScreen
import com.trupermax.cineverse.ui.screens.HomeScreen
import com.trupermax.cineverse.ui.screens.MovieDetailScreen
import com.trupermax.cineverse.ui.screens.SearchScreen
import com.trupermax.cineverse.navigation.Routes.*
import com.trupermax.cineverse.ui.components.BottomNavItem
import com.trupermax.cineverse.ui.components.BottomNavigationBar

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
        Routes.HomeRoute
    )

    val currentRoute = backStack.last()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                currentRoute = currentRoute,
                onItemSelected = { route ->
                    if (currentRoute != route) {
                        while (backStack.size > 1 ) {
                            backStack.removeLastOrNull()
                        }

                        if (route != HomeRoute) {
                            backStack.add(route)
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(innerPadding),
            backStack = backStack,
            onBack = {
                backStack.removeLastOrNull()
            },
            entryProvider = entryProvider {
                entry<HomeRoute> {
                    HomeScreen()
                }

                entry<SearchRoute> {
                    SearchScreen()
                }

                entry<FavoritesRoute> {
                    FavoritesScreen()
                }

                entry<MovieDetailsRoute> { route ->
                    MovieDetailScreen()
                }
            }
        )
    }
}