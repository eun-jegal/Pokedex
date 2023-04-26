package com.example.pokedex.pokemondetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import com.example.pokedex.R
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.Type
import com.example.pokedex.util.*
import java.util.*

@Composable
fun PokemonDetailScreen(
    dominantColor: Int,
    name: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailViewModel = hiltNavGraphViewModel()
) {
    val pokemonDetail = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonDetail(name)
    }.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
            .background(Color(dominantColor))
    ) {
        TopBar(navController = navController)
        PokemonDetailStateWrapper(
            pokemonDetail = pokemonDetail,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = topPadding + pokemonImageSize / 2,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
                .shadow(10.dp, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.surface),
            loadingModifier = Modifier
                .size(100.dp)
                .padding(
                    top = topPadding + pokemonImageSize / 2,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        )
    }
}

@Composable
fun TopBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Navigate Up",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_border_24),
            contentDescription = "Add to Favorites",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
    }
}

@Composable
fun PokemonDetailStateWrapper(
    pokemonDetail: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (pokemonDetail) {
        is Resource.Success -> {
            PokemonDetailSection(
                pokemonDetail = pokemonDetail.data!!,
                modifier = Modifier.offset(y = (-20).dp)
            )
        }
        is Resource.Error -> {
            Text(
                text = pokemonDetail.message!!,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }
    }
}

@Composable
fun PokemonDetailSection(
    pokemonDetail: Pokemon,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        PokemonNumberAndName(
            number = pokemonDetail.order,
            name = pokemonDetail.name
        )
        Spacer(modifier = Modifier.height(8.dp))

        PokemonTypes(types = pokemonDetail.types)
        Spacer(modifier = Modifier.height(16.dp))

        PokemonImage(imageUrl = "")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
        ) {
            PokemonAbout(pokemonDetail = pokemonDetail)
            Spacer(modifier = Modifier.height(16.dp))
            PokemonBaseStats(pokemonDetail = pokemonDetail)
        }
    }
}

@Composable
fun PokemonNumberAndName(
    number: Int,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = reformatNum(number), fontWeight = FontWeight.Medium, fontSize = 20.sp)
        Text(text = name, fontWeight = FontWeight.SemiBold, fontSize = 28.sp)
    }
}

@Composable
fun PokemonTypes(types: List<Type>) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (type in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clip(CircleShape)
                    .background(parseTypeToColor(type))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = type.type.name.capitalize(Locale.ROOT),
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun PokemonImage(
    imageUrl: String
) {

}

@Composable
fun PokemonAbout(
    pokemonDetail: Pokemon
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "About",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        PokemonDetailRow(title = "Height", data = "${pokemonDetail.height} m")
        PokemonDetailRow(title = "Weight", data = "${pokemonDetail.weight} kg")
        var abilities = ""
        pokemonDetail.abilities.forEachIndexed { index, ability ->
            if (index != pokemonDetail.abilities.lastIndexOf(ability)) {
                abilities += "${ability.ability.name}, "
            }
        }
        PokemonDetailRow(title = "Abilities", data = abilities)
    }
}

@Composable
fun PokemonDetailRow(
    title: String,
    data: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(0.3f),
            fontSize = 14.sp
        )
        Text(
            text = data,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(0.7f),
            fontSize = 14.sp
        )
    }
}

@Composable
fun PokemonBaseStats(
    pokemonDetail: Pokemon,
    animDelayPerItem: Int = 100
) {
    val maxBaseStat = remember {
        pokemonDetail.stats.maxOf { it.baseStat }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Base stats",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(8.dp))
        for (i in pokemonDetail.stats.indices) {
            val stat = pokemonDetail.stats[i]
            PokemonStat(
                statName = parseStatToAbbr(stat),
                statValue = stat.baseStat,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat),
                animDelay = i * animDelayPerItem
            )
        }
    }
}

@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 10.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(0.2f),
            fontSize = 14.sp
        )
        Row(
            modifier = Modifier.weight(0.8f)
        ) {
            Text(
                text = (currentPercent.value * statMaxValue).toInt().toString(),
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(0.1f),
                fontSize = 14.sp
            )
            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .height(height)
                    .clip(CircleShape)
                    .background(
                        if (isSystemInDarkTheme()) {
                            Color(0xFF505050)
                        } else {
                            Color.LightGray
                        }
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(currentPercent.value)
                        .clip(CircleShape)
                        .background(statColor)
                        .padding(horizontal = 8.dp)
                ) {}
            }
        }
    }

}