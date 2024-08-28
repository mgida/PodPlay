package com.example.podplay.presentation.podcast_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podplay.domain.model.podcast_details.PodcastDetailsModel
import com.example.podplay.presentation.podcast_details.viewmodel.PodcastDetailsViewModel
import timber.log.Timber


@Composable
fun PodcastDetails(
    modifier: Modifier = Modifier,
    viewModel: PodcastDetailsViewModel = hiltViewModel()
) {

    val podcastDetailsState = viewModel.podcastDetailsState.collectAsState().value

    SideEffect {
        Timber.d("Podcast details $podcastDetailsState")
    }

    when {
        podcastDetailsState.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.width(24.dp))
            }
        }

        podcastDetailsState.error.isNotEmpty() -> {
            ErrorState(modifier = Modifier.fillMaxSize(), errorMsg = podcastDetailsState.error)
        }

        podcastDetailsState.data != null -> {
            PodcastDetailsContent(modifier, podcastDetailsState.data)
        }
    }
}

@Composable
private fun PodcastDetailsContent(
    modifier: Modifier,
    data: PodcastDetailsModel
) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = data.title,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = data.description,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "${data.episodes.count()} Episodes",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Red
        )
    }
}


@Composable
fun ErrorState(modifier: Modifier = Modifier, errorMsg: String) {
    Box(
        modifier = modifier
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMsg,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PodcastDetailsContentPreview() {
    val dummyPodcast = PodcastDetailsModel(
        id = "podcast_001",
        type = "Technology",
        image = "https://example.com/images/podcast_001.jpg",
        title = "Tech Talks Daily",
        episodes = listOf(),
        publisher = "Tech Daily Media",
        thumbnail = "https://example.com/thumbnails/podcast_001.jpg",
        description = "Daily insights into the world of technology, AI, cybersecurity, and more.",
        hasSponsors = true,
        totalEpisodes = 2,
        listenNotesUrl = "https://listennotes.com/podcast_001",
        audioLengthSec = 6400
    )

    PodcastDetailsContent(
        modifier = Modifier.fillMaxSize(), data = dummyPodcast
    )
}


