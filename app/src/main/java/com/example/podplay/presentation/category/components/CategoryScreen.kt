package com.example.podplay.presentation.category.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.podplay.domain.model.best_podcasts.BestPodcastModel
import com.example.podplay.presentation.category.CategoryViewModel


@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoryViewModel = hiltViewModel()
) {

    val categoryPodcastsState = viewModel.categoryPodcasts.collectAsState().value

    when {
        categoryPodcastsState.isLoading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            }
        }

        categoryPodcastsState.error.isNotEmpty() -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = categoryPodcastsState.error,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Red
                )
            }
        }

        categoryPodcastsState.data.isNotEmpty() -> {
            CategoryScreenContent(categoryPodcastsState.data)
        }
    }
}

@Composable
fun CategoryScreenContent(data: List<BestPodcastModel>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)
    ) {

        items(data, key = { it.id }) {
            CategoryPodcastItem(it)
        }
    }
}

@Composable
fun CategoryPodcastItem(bestPodcastModel: BestPodcastModel, modifier: Modifier = Modifier) {

    Text(
        text = bestPodcastModel.title,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Black,
        modifier = modifier.padding(8.dp)
    )
}
