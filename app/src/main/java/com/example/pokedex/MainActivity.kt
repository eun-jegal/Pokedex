package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.pokemondetail.PokemonDetailScreen
import com.example.pokedex.pokemonlist.PokemonListScreen
import com.example.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen"
                ) {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        route = "pokemon_detail_screen/{dominantColor}/{pokemonName}/{pokemonNum}",
                        arguments = listOf(
                            navArgument("dominantColor") {
                                type = NavType.IntType
                            },
                            navArgument("pokemonName") {
                                type = NavType.StringType
                            },
                            navArgument("pokemonNum") {
                                type = NavType.IntType
                            }
                        )) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt("dominantColor")
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString("pokemonName")
                        }
                        val pokemonNum = remember {
                            it.arguments?.getInt("pokemonNum")
                        }
                        PokemonDetailScreen(
                            dominantColor = dominantColor.toArgb(),
                            name = pokemonName?.toLowerCase(Locale.ROOT) ?: "",
                            number = pokemonNum ?: 0,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}