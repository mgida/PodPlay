package com.example.podplay.presentation.category


sealed class CategoryPodcastsEvent {
    data class GetPodcastsByCategory(val categoryId: String) : CategoryPodcastsEvent()
}