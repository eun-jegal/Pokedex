package com.example.pokedex.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Screen(
    val route: String,
    val icon: ImageVector? = null,
    val label: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    object Home : Screen(
        route = "pokemon_list_screen",
        icon = Icons.Default.Home,
        label = "Home"
    )

    object Detail : Screen(
        route = "pokemon_detail_screen/{dominantColor}/{pokemonName}/{pokemonNum}",
        label = "Favorite",
        navArguments = listOf(
            navArgument("dominantColor") {
                type = NavType.IntType
            },
            navArgument("pokemonName") {
                type = NavType.StringType
            },
            navArgument("pokemonNum") {
                type = NavType.IntType
            }
        )
    )

    object Saved : Screen(
        route = "pokemon_favorite_screen",
        icon = Icons.Default.Favorite,
        label = "Favorite"
    )
}
