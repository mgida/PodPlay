package com.example.podplay.data.dto.best_podcasts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LookingFor(
    val cohosts: Boolean?,
    @Json(name = "crossPromotion")
    val crossPromotion: Boolean?,
    val guests: Boolean?,
    val sponsors: Boolean?
)