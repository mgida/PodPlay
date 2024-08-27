package com.example.podplay.data.remote

import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.data.dto.genres.GenresResponse
import com.example.podplay.data.dto.podcast_details.PodcastDetailsResponse
import com.example.podplay.util.DEFAULT_SORT
import com.example.podplay.util.SHOW_TOP_LEVEL
import retrofit2.http.GET
import retrofit2.http.Path
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

    @Authorized
    @GET("podcasts/{id}")
    suspend fun getPodcastDetails(
        @Path("id") id: String,
        @Query("sort") sortBy: String = DEFAULT_SORT
    ): PodcastDetailsResponse


}






