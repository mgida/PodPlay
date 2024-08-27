package com.example.podplay.domain.repo

import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.data.dto.genres.GenresResponse
import com.example.podplay.data.dto.podcast_details.PodcastDetailsResponse

interface PodcastRepo {
    suspend fun getBestPodcasts(): BestPodcastsResponse
    suspend fun getGenres(): GenresResponse
    suspend fun getPodcastDetails(id: String): PodcastDetailsResponse
}