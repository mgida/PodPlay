package com.example.podplay.util

import androidx.navigation.NavType
import androidx.navigation.navArgument


private const val HOME_PODCASTS = "home_podcasts"
private const val PODCAST_DETAIL = "podcast_details"
private const val CATEGORY = "category"
const val PODCAST_ID_ARG = "podcastId"
const val CATEGORY_ID_ARG = "categoryId"

sealed class Screen(val route: String) {
    data object HomePodcasts : Screen(HOME_PODCASTS)

    data object PodcastDetails : Screen(PODCAST_DETAIL) {

        val routeWithArgs = "$route?$PODCAST_ID_ARG={$PODCAST_ID_ARG}"
        fun createRoute(podcastId: String) = "$route?$PODCAST_ID_ARG=$podcastId"

        val navArgument = listOf(navArgument(name = PODCAST_ID_ARG) {
            type = NavType.StringType
            defaultValue = ""
        })
    }

    data object Category : Screen(CATEGORY) {

        val routeWithArgs = "$route?$CATEGORY_ID_ARG={$CATEGORY_ID_ARG}"
        fun createRoute(categoryId: String) = "$route?$CATEGORY_ID_ARG=$categoryId"

        val navArgument = listOf(navArgument(name = CATEGORY_ID_ARG) {
            type = NavType.StringType
            defaultValue = ""
        })
    }
}