package com.example.podplay.domain.mapper

import com.example.podplay.data.dto.podcast_details.PodcastDetailsResponse
import com.example.podplay.domain.model.podcast_details.PodcastDetailsModel
import javax.inject.Inject

class UIPodcastDetailsMapper @Inject constructor(
    private val uiEpisodesMapper: UIEpisodesMapper
) :
    IMapper<PodcastDetailsResponse, PodcastDetailsModel> {
    override fun map(input: PodcastDetailsResponse): PodcastDetailsModel {
        return PodcastDetailsModel(
            id = input.id.orEmpty(),
            type = input.type.orEmpty(),
            image = input.image.orEmpty(),
            title = input.title.orEmpty(),
            episodes = uiEpisodesMapper.map(input = input.episodes ?: listOf()),
            publisher = input.publisher.orEmpty(),
            thumbnail = input.thumbnail.orEmpty(),
            description = input.description.orEmpty(),
            hasSponsors = input.hasSponsors ?: false,
            totalEpisodes = input.totalEpisodes ?: 0,
            listenNotesUrl = input.listennotesUrl.orEmpty(),
            audioLengthSec = input.audioLengthSec ?: 0
        )
    }
}