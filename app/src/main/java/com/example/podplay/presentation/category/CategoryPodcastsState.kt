package com.example.podplay.presentation.category

import com.example.podplay.domain.model.best_podcasts.BestPodcastModel

data class CategoryPodcastsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<BestPodcastModel> = emptyList()
)