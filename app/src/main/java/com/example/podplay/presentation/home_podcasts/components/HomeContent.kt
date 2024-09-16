package com.example.podplay.presentation.home_podcasts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.podplay.R
import com.example.podplay.domain.model.best_podcasts.BestPodcastModel
import com.example.podplay.domain.model.genres.GenreModel
import com.example.podplay.presentation.home_podcasts.best_podcast.BestPodcastsState
import com.example.podplay.presentation.home_podcasts.genres.GenresState
import com.example.podplay.presentation.home_podcasts.viewmodel.BestPodcastsViewModel
import com.example.podplay.ui.theme.PodPlayTheme
import com.example.podplay.util.ThemePreviews
import timber.log.Timber


@Composable
fun HomeContent(
    modifier: Modifier = Modifier, viewModel: BestPodcastsViewModel = hiltViewModel(),
    onNavigateToDetails: (podcastId: String) -> Unit,
    onNavigateToCategory: (categoryId: Int) -> Unit
) {

    val bestPodCasts = viewModel.bestPodcastsState.collectAsState().value
    val genresState = viewModel.genresState.collectAsState().value

    SideEffect {
        Timber.d("Podcasts: $bestPodCasts,, genres: $genresState")
    }

    BestPodcasts(
        modifier,
        bestPodCasts,
        genresState,
        onNavigateToDetails,
        onNavigateToCategory
    )
}

@Composable
private fun BestPodcasts(
    modifier: Modifier,
    bestPodCasts: BestPodcastsState,
    categories: GenresState,
    onNavigateToDetails: (podcastId: String) -> Unit,
    onNavigateToCategory: (categoryId: Int) -> Unit,
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {

        item {
            CategoriesSection(categories, onNavigateToCategory)
        }
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }


        when {
            bestPodCasts.isLoading -> {
                item {
                    ProgressLoading()
                }
            }

            bestPodCasts.error.isNotEmpty() -> {
                item {
                    ErrorMsg(msg = bestPodCasts.error)
                }
            }

            bestPodCasts.data.isNotEmpty() -> {
                items(bestPodCasts.data) { bestPodCast ->
                    BestPodcastItem(modifier = Modifier.clickable {
                        onNavigateToDetails.invoke(bestPodCast.id)
                    }, bestPodCast)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
private fun CategoriesSection(
    categories: GenresState,
    onNavigateToCategory: (categoryId: Int) -> Unit
) {

    when {
        categories.isLoading -> {
            ProgressLoading()
        }

        categories.error.isNotEmpty() -> {
            ErrorMsg(categories.error)
        }

        categories.data.isNotEmpty() -> {
            Categories(categories = categories.data) { categoryId ->
                onNavigateToCategory.invoke(categoryId)
            }
        }
    }
}

@Composable
private fun ErrorMsg(msg: String) {
    Text(
        text = msg,
        style = MaterialTheme.typography.titleMedium,
        color = Color.Red,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ProgressLoading() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(24.dp))
    }
}

@Composable
private fun BestPodcastItem(modifier: Modifier = Modifier, bestPodcastModel: BestPodcastModel) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {

            Image(
                painter = rememberAsyncImagePainter(
                    bestPodcastModel.image,
                    placeholder = painterResource(
                        id = R.drawable.kotlin_podcast
                    )
                ),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 130.dp, height = 120.dp)
                    .clip(RoundedCornerShape(percent = 12))
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = modifier.padding(horizontal = 16.dp),
                    text = bestPodcastModel.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    text = bestPodcastModel.description,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8F),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@ThemePreviews
@Composable
fun BestPodcastItemPreview() {

    PodPlayTheme {
        BestPodcastItem(
            bestPodcastModel = BestPodcastModel(
                id = "", image = "", title = "Talking Kotlin..",
                description = "With Kotlin Multiplatform, you can build cross-platform mobile applications that share code between Android and iOS projects"
            )
        )
    }
}

@ThemePreviews
@Composable
fun BestPodcastsPreview() {

    val dummyPodcasts = (0..3).map {
        BestPodcastModel(
            id = "",
            image = "",
            title = "Tech",
            description = "With Kotlin Multiplatform, you can build cross-platform mobile applications that share code between Android and iOS projects"
        )
    }

    PodPlayTheme {
        BestPodcasts(
            modifier = Modifier.fillMaxSize(),
            bestPodCasts = BestPodcastsState(
                data = dummyPodcasts
            ),
            categories = GenresState(
                data = listOf(
                    GenreModel(id = 1, name = "Tech"),
                    GenreModel(id = 2, name = "Comedy"),
                )
            ), {}
        ) {}
    }
}