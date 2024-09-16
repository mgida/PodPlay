package com.example.podplay.domain.use_case

import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.domain.repo.PodcastRepo
import com.example.podplay.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetBestPodcastsUseCase @Inject constructor(private val repo: PodcastRepo) {
    operator fun invoke(genreId: String = ""): Flow<Resource<BestPodcastsResponse>> = flow {
        emit(Resource.Loading())

        try {
            val bestPodcasts = repo.getBestPodcasts(genreId = genreId)
            emit(Resource.Success(data = bestPodcasts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}