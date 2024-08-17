package com.example.podplay.presentation

import com.example.podplay.domain.model.best_podcasts.BestPodcastModel

data class BestPodcastsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<BestPodcastModel> = emptyList()
)
