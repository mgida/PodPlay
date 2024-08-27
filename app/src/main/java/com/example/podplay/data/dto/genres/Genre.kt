package com.example.podplay.data.dto.genres

import com.squareup.moshi.Json

data class GenreResponse(
    val id: Int?,
    val name: String?,
    @Json(name = "parent_id")
    val parentId: Int?
)