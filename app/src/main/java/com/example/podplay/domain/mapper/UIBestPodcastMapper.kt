package com.example.podplay.domain.mapper

import com.example.podplay.data.dto.best_podcasts.PodcastResponse
import com.example.podplay.domain.model.best_podcasts.BestPodcastModel
import javax.inject.Inject

class UIBestPodcastMapper @Inject constructor() : IMapper<PodcastResponse, BestPodcastModel> {
    override fun map(input: PodcastResponse): BestPodcastModel {
        return BestPodcastModel(
            id = input.id ?: "",
            title = input.title ?: "",
            image = input.image ?: "",
            description = input.description.orEmpty()
        )
    }
}