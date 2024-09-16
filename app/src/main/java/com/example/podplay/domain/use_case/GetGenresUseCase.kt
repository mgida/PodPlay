package com.example.podplay.domain.use_case

import com.example.podplay.data.dto.genres.GenresResponse
import com.example.podplay.domain.repo.PodcastRepo
import com.example.podplay.util.Resource
import com.example.podplay.util.SHOW_TOP_LEVEL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(private val repo: PodcastRepo) {
    operator fun invoke(topLevel: Int = SHOW_TOP_LEVEL): Flow<Resource<GenresResponse>> = flow {
        emit(Resource.Loading())

        try {
            val genres = repo.getGenres(topLevel = topLevel)
            emit(Resource.Success(data = genres))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage.orEmpty()))
        }
    }
}