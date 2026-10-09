package com.trupermax.cineverse.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.trupermax.cineverse.data.sampleMovies

@Composable
fun MovieDetailScreen(
    movieId: Int,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBack: () -> Unit
) {
    val movie = sampleMovies.find { movie ->
        movie.id == movieId
    }

    if (movie == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Película no encontrada",
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
        ) {
            Image(
                painter = painterResource(
                    id = movie.posterResId
                ),
                contentDescription = "Póster de ${movie.title}",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to Color.Transparent,
                                0.45f to Color.Transparent,
                                0.70f to MaterialTheme.colorScheme.background.copy(
                                    alpha = 0.70f
                                ),
                                1.0f to MaterialTheme.colorScheme.background
                            )
                        )
                    )
            )

            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(
                            alpha = 0.55f
                        ),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(
                onClick = onFavoriteClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background.copy(
                            alpha = 0.55f
                        ),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = if (isFavorite) {
                        "Quitar de favoritos"
                    } else {
                        "Agregar a favoritos"
                    },
                    tint = if (isFavorite) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )
            }

            Text(
                text = movie.title,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        horizontal = 20.dp,
                        vertical = 24.dp
                    ),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 8.dp,
                    bottom = 32.dp
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = movie.releaseYear.toString(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Sinopsis",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = movie.overview,
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 22.sp
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "Géneros",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    movie.genres.forEach { genre ->

                        Surface(
                            shape = RoundedCornerShape(50),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {

                            Text(
                                text = genre,
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 6.dp
                                ),
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}