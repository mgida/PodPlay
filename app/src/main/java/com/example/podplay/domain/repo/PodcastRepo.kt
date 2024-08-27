package com.example.podplay.domain.repo

import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.data.dto.genres.GenresResponse

interface PodcastRepo {
    suspend fun getBestPodcasts(): BestPodcastsResponse
    suspend fun getGenres(): GenresResponse
}