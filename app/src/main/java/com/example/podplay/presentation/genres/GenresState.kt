package com.example.podplay.presentation.genres

import com.example.podplay.domain.model.genres.GenreModel

data class GenresState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<GenreModel> = emptyList()
)
