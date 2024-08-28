package com.example.podplay.presentation.home_podcasts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podplay.domain.model.best_podcasts.BestPodcastModel
import com.example.podplay.presentation.home_podcasts.best_podcast.BestPodcastsState
import com.example.podplay.presentation.home_podcasts.viewmodel.BestPodcastsViewModel
import timber.log.Timber


@Composable
fun HomeContent(
    modifier: Modifier = Modifier, viewModel: BestPodcastsViewModel = hiltViewModel(),
    onNavigateToDetails: (podcastId: String) -> Unit
) {

    val bestPodCasts = viewModel.bestPodcastsState.collectAsState().value
    val genresState = viewModel.genresState.collectAsState().value

    SideEffect {
        Timber.d("Podcasts: $bestPodCasts,, genres: $genresState")
    }

    BestPodcasts(modifier, bestPodCasts, onNavigateToDetails)

}

@Composable
private fun BestPodcasts(
    modifier: Modifier,
    bestPodCasts: BestPodcastsState,
    onNavigateToDetails: (podcastId: String) -> Unit
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        items(bestPodCasts.data) { bestPodCast ->
            BestPodcastItem(modifier = Modifier.clickable {
                onNavigateToDetails.invoke(bestPodCast.id)
            }, bestPodCast)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
fun BestPodcastsPreview() {

    val dummyPodcasts = (0..3).map {
        BestPodcastModel(
            id = "",
            image = "",
            title = "Tech"
        )
    }

    BestPodcasts(
        modifier = Modifier.fillMaxSize(),
        bestPodCasts = BestPodcastsState(
            data = dummyPodcasts
        )
    ) {}
}

@Composable
private fun BestPodcastItem(modifier: Modifier = Modifier, bestPodcastModel: BestPodcastModel) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = modifier.padding(16.dp),
            text = bestPodcastModel.title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun BestPodcastItemPreview() {

    BestPodcastItem(
        bestPodcastModel = BestPodcastModel(
            id = "", image = "", title = "Tech"
        )
    )
}