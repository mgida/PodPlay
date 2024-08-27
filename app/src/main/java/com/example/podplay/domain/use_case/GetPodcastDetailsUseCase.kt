package com.example.podplay.domain.use_case

import com.example.podplay.data.dto.podcast_details.PodcastDetailsResponse
import com.example.podplay.domain.repo.PodcastRepo
import com.example.podplay.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPodcastDetailsUseCase @Inject constructor(private val repo: PodcastRepo) {
    operator fun invoke(podcastId: String): Flow<Resource<PodcastDetailsResponse>> = flow {
        emit(Resource.Loading())

        try {
            val podcastDetails = repo.getPodcastDetails(podcastId)
            emit(Resource.Success(data = podcastDetails))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: ""))
        }
    }
}