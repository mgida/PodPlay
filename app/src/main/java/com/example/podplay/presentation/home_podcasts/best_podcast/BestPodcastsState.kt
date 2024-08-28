package com.example.podplay.presentation.home_podcasts.best_podcast

import com.example.podplay.domain.model.best_podcasts.BestPodcastModel

data class BestPodcastsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<BestPodcastModel> = emptyList()
)
