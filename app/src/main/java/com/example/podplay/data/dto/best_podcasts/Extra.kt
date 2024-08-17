package com.example.podplay.data.dto.best_podcasts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Extra(
    @Json(name = "amazon_music_url")
    val amazonMusicUrl: String?,
    @Json(name = "facebook_handle")
    val facebookHandle: String?,
    @Json(name = "google_url")
    val googleUrl: String?,
    @Json(name = "instagram_handle")
    val instagramHandle: String?,
    @Json(name = "linkedin_url")
    val linkedinUrl: String?,
    @Json(name = "patreon_handle")
    val patreonHandle: String?,
    @Json(name = "spotify_url")
    val spotifyUrl: String?,
    @Json(name = "twitter_handle")
    val twitterHandle: String?,
    val url1: String?,
    val url2: String?,
    val url3: String?,
    @Json(name = "wechat_handle")
    val wechatHandle: String?,
    @Json(name = "youtube_url")
    val youtubeUrl: String?
)