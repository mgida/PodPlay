package com.example.podplay.domain.mapper

import com.example.podplay.data.dto.best_podcasts.PodcastResponse
import com.example.podplay.domain.model.best_podcasts.BestPodcastModel
import javax.inject.Inject

class UIBestPodcastsMapper @Inject constructor() :
    IMapper<List<PodcastResponse>, List<BestPodcastModel>> {
    override fun map(input: List<PodcastResponse>): List<BestPodcastModel> {
        val bestPodcasts = mutableListOf<BestPodcastModel>()
        for (podcastResponse in input) {
            bestPodcasts.add(
                BestPodcastModel(
                    id = podcastResponse.id ?: "",
                    title = podcastResponse.title ?: "",
                    image = podcastResponse.image ?: ""
                )
            )
        }
        return bestPodcasts
    }
}