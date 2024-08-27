package com.example.podplay.domain.model.podcast_details

data class PodcastDetailsModel(
    val id: String,
    val type: String,
    val image: String,
    val title: String,
    val episodes: List<EpisodeModel>,
    val publisher: String,
    val thumbnail: String,
    val description: String,
    val hasSponsors: Boolean,
    val listenScore: Int,
    val totalEpisodes: Int,
    val listenNotesUrl: String,
    val audioLengthSec: Int
)

data class EpisodeModel(
    val id: String,
    val link: String,
    val audio: String,
    val image: String,
    val title: String,
    val thumbnail: String,
    val description: String,
    val listennotesUrl: String,
    val audioLengthSec: Int
)