package com.example.podplay.data.dto.podcast_details

import com.squareup.moshi.Json

data class PodcastDetailsResponse(
    @Json(name = "audio_length_sec")
    val audioLengthSec: Int?,
    val country: String?,
    val description: String?,
    @Json(name = "earliest_pub_date_ms")
    val earliestPubDateMs: Long?,
    val email: String?,
    val episodes: List<EpisodeResponse>?,
    @Json(name = "explicit_content")
    val explicitContent: Boolean?,
    val extra: Extra?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>?,
    @Json(name = "has_guest_interviews")
    val hasGuestInterviews: Boolean?,
    @Json(name = "has_sponsors")
    val hasSponsors: Boolean?,
    val id: String?,
    val image: String?,
    @Json(name = "is_claimed")
    val isClaimed: Boolean?,
    @Json(name = "itunes_id")
    val itunesId: Int?,
    val language: String?,
    @Json(name = "latest_episode_id")
    val latestEpisodeId: String?,
    @Json(name = "latest_pub_date_ms")
    val latestPubDateMs: Long?,
    @Json(name = "listen_score")
    val listenScore: Int?,
    @Json(name = "listen_score_global_rank")
    val listenScoreGlobalRank: String?,
    @Json(name = "listennotes_url")
    val listennotesUrl: String?,
    @Json(name = "looking_for")
    val lookingFor: LookingFor?,
    @Json(name = "next_episode_pub_date")
    val nextEpisodePubDate: Long?,
    val publisher: String?,
    val rss: String?,
    val thumbnail: String?,
    val title: String?,
    @Json(name = "total_episodes")
    val totalEpisodes: Int?,
    val type: String?,
    @Json(name = "update_frequency_hours")
    val updateFrequencyHours: Int?,
    val website: String?
)