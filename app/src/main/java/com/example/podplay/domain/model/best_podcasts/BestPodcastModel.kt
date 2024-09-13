package com.example.podplay.domain.model.best_podcasts

import androidx.compose.runtime.Immutable

@Immutable
data class BestPodcastModel(
    val id: String,
    val image: String,
    val title: String
)
