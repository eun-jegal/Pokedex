package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.ui.Screen
import com.example.pokedex.ui.favoritepokemon.FavoritePokemonScreen
import com.example.pokedex.ui.pokemondetail.PokemonDetailScreen
import com.example.pokedex.ui.pokemonlist.PokemonListScreen
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
                Scaffold(bottomBar = { BottomNavigationBar(navController = navController)}) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(Screen.Home.route) {
                            PokemonListScreen(navController = navController)
                        }
                        composable(Screen.Saved.route) {
                            FavoritePokemonScreen()
                        }
                        composable(
                            route = Screen.Detail.route,
                            arguments =  Screen.Detail.navArguments
                        ) {
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
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navItems = listOf(Screen.Home, Screen.Saved)
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navItems.forEach { item ->
            BottomNavigationItem(
                icon = {
                    item.icon?.let {
                        Icon(imageVector = it, contentDescription = item.label)
                    }
                },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}