package com.example.podplay.data.dto.best_podcasts

import com.google.gson.annotations.SerializedName

data class Podcast(
    @SerializedName("audio_length_sec")
    val audioLengthSec: Int,
    val country: String,
    val description: String,
    @SerializedName("earliest_pub_date_ms")
    val earliestPubDateMs: Long,
    val email: String,
    val explicit_content: Boolean,
    val extra: Extra,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("has_guest_interviews")
    val hasGuestInterviews: Boolean,
    @SerializedName("has_sponsors")
    val hasSponsors: Boolean,
    val id: String,
    val image: String,
    @SerializedName("is_claimed")
    val is_claimed: Boolean,
    val itunes_id: Int,
    val language: String,
    val latest_episode_id: String,
    val latest_pub_date_ms: Long,
    val listen_score: String,
    val listen_score_global_rank: String,
    val listennotes_url: String,
    val looking_for: LookingFor,
    val publisher: String,
    val rss: String,
    val thumbnail: String,
    val title: String,
    @SerializedName("total_episodes")
    val total_episodes: Int,
    val type: String,
    val update_frequency_hours: Int,
    val website: String
)