package com.example.podplay.presentation.podcast_details

import com.example.podplay.domain.model.podcast_details.PodcastDetailsModel

data class PodcastDetailsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: PodcastDetailsModel?= null
)
