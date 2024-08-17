package com.example.podplay.data.dto.best_podcasts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BestPodcastsResponse(

    @Json(name = "has_next")
    val hasNext: Boolean?,
    @Json(name = "has_previous")
    val hasPrevious: Boolean?,
    val id: Int?,
    @Json(name = "listennotes_url")
    val listenNotesUrl: String?,
    val name: String?,
    @Json(name = "next_page_number")
    val nextPageNumber: Int?,
    @Json(name = "page_number")
    val pageNumber: Int?,
    val podcasts: List<PodcastResponse>,
    @Json(name = "previous_page_number")
    val previousPageNumber: Int?,
    val total: Int?
)