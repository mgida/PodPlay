package com.example.podplay.data.dto.podcast_details

import com.squareup.moshi.Json

data class LookingFor(
    val cohosts: Boolean?,
    @Json(name = "cross_promotion")
    val crossPromotion: Boolean?,
    val guests: Boolean?,
    val sponsors: Boolean?
)