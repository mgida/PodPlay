package com.example.podplay.data.dto.best_podcasts

import com.google.gson.annotations.SerializedName

data class Extra(
    @SerializedName("amazon_music_url")
    val amazonMusicUrl: String,
    @SerializedName("facebook_handle")
    val facebookHandle: String,
    @SerializedName("google_url")
    val googleUrl: String,
    val instagram_handle: String,
    val linkedin_url: String,
    val patreon_handle: String,
    val spotify_url: String,
    val twitter_handle: String,
    val url1: String,
    val url2: String,
    val url3: String,
    val wechat_handle: String,
    val youtube_url: String
)