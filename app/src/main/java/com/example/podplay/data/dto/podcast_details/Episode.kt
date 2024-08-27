package com.example.podplay.data.dto.podcast_details

import com.squareup.moshi.Json

data class EpisodeResponse(
    val audio: String?,
    @Json(name = "audio_length_sec")
    val audioLengthSec: Int?,
    val description: String?,
    @Json(name = "explicit_content")
    val explicitContent: Boolean?,
    val id: String?,
    val image: String?,
    val link: String?,
    @Json(name = "listennotes_url")
    val listennotesUrl: String?,
    @Json(name = "maybe_audio_invalid")
    val maybeAudioInvalid: Boolean?,
    @Json(name = "pub_date_ms")
    val pubDateMs: Long?,
    val thumbnail: String?,
    val title: String?
)