package com.example.podplay.data.dto.podcast_details

import com.squareup.moshi.Json

data class Extra(
    @Json(name = "youtube_url")
    val youtubeUrl: String?
)