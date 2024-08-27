package com.example.podplay.domain.mapper

import com.example.podplay.data.dto.genres.GenreResponse
import com.example.podplay.domain.model.genres.GenreModel
import javax.inject.Inject

class UIGenreMapper @Inject constructor() : IMapper<GenreResponse, GenreModel> {
    override fun map(input: GenreResponse): GenreModel {
        return GenreModel(
            id = input.id ?: 0,
            name = input.name.orEmpty()
        )
    }
}