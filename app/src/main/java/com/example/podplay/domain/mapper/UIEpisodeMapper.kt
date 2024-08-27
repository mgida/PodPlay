package com.example.podplay.domain.mapper

import com.example.podplay.data.dto.podcast_details.EpisodeResponse
import com.example.podplay.domain.model.podcast_details.EpisodeModel
import javax.inject.Inject

class UIEpisodesMapper @Inject constructor() : IMapper<List<EpisodeResponse>, List<EpisodeModel>> {
    override fun map(input: List<EpisodeResponse>): List<EpisodeModel> {
        val episodes = mutableListOf<EpisodeModel>()

        for (episodeResponse in input) {
            episodes.add(
                EpisodeModel(
                    id = episodeResponse.id.orEmpty(),
                    link = episodeResponse.link.orEmpty(),
                    audio = episodeResponse.audio.orEmpty(),
                    image = episodeResponse.image.orEmpty(),
                    title = episodeResponse.title.orEmpty(),
                    thumbnail = episodeResponse.thumbnail.orEmpty(),
                    description = episodeResponse.description.orEmpty(),
                    listennotesUrl = episodeResponse.listennotesUrl.orEmpty(),
                    audioLengthSec = episodeResponse.audioLengthSec ?: 0
                )
            )
        }
        return episodes
    }
}