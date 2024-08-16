package com.example.podplay.data.remote

import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import retrofit2.http.GET

interface PodcastRemoteDataSource {

    @Authorized
    @GET("best_podcasts")
    suspend fun getBestPodcasts(): BestPodcastsResponse
}






