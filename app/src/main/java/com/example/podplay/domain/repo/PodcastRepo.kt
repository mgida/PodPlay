package com.example.podplay.domain.repo

import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse

interface PodcastRepo {
    suspend fun getBestPodcasts(): BestPodcastsResponse
}