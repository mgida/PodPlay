package com.example.podplay.data.remote

import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.data.dto.genres.GenresResponse
import com.example.podplay.util.SHOW_TOP_LEVEL
import retrofit2.http.GET
import retrofit2.http.Query

interface PodcastRemoteDataSource {

    @Authorized
    @GET("best_podcasts")
    suspend fun getBestPodcasts(): BestPodcastsResponse

    @Authorized
    @GET("genres")
    suspend fun getGenres(
        @Query("top_level_only") topLevelOnly: Int = SHOW_TOP_LEVEL
    ): GenresResponse

}






