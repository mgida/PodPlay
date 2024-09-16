package com.example.podplay.presentation.nav_host

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.podplay.presentation.category.components.CategoryScreen
import com.example.podplay.presentation.home_podcasts.components.HomeContent
import com.example.podplay.presentation.podcast_details.components.PodcastDetails
import com.example.podplay.util.Screen


@Composable
fun PodcastNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomePodcasts.route,
        modifier = modifier
    ) {

        composable(
            route = Screen.HomePodcasts.route,
        ) {
            HomeContent(modifier = Modifier.fillMaxSize(), onNavigateToDetails = { podcastId ->
                navigateToDetailsScreen(navController, podcastId)
            }, onNavigateToCategory = { categoryId ->
                navigateToCategoryScreen(navController, categoryId)
            })
        }

        composable(
            route = Screen.PodcastDetails.routeWithArgs,
            arguments = Screen.PodcastDetails.navArgument
        ) {
            PodcastDetails(modifier = Modifier.fillMaxSize())
        }

        composable(
            route = Screen.Category.routeWithArgs,
            arguments = Screen.Category.navArgument
        ) {
            CategoryScreen(modifier = Modifier.fillMaxSize())
        }
    }
}

fun navigateToCategoryScreen(navController: NavHostController, categoryId: Int) {
    navController.navigate(Screen.Category.createRoute(categoryId = categoryId.toString())) {
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetailsScreen(navController: NavHostController, podcastId: String) {
    navController.navigate(Screen.PodcastDetails.createRoute(podcastId = podcastId)) {
        launchSingleTop = true
        restoreState = true
    }
}
