package com.example.podplay.domain.mapper

import com.example.podplay.data.dto.genres.GenreResponse
import com.example.podplay.domain.model.genres.GenreModel
import javax.inject.Inject

class UIGenresMapper @Inject constructor() :
    IMapper<List<GenreResponse>, List<GenreModel>> {
    override fun map(input: List<GenreResponse>): List<GenreModel> {
        val genres = mutableListOf<GenreModel>()
        for (genre in input) {
            genres.add(
                GenreModel(
                    id = genre.id ?: 0,
                    name = genre.name.orEmpty()
                )
            )
        }
        return genres
    }
}