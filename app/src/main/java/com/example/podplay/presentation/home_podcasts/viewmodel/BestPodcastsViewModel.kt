package com.example.podplay.presentation.home_podcasts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.data.dto.genres.GenresResponse
import com.example.podplay.domain.mapper.UIBestPodcastsMapper
import com.example.podplay.domain.mapper.UIGenresMapper
import com.example.podplay.domain.use_case.GetBestPodcastsUseCase
import com.example.podplay.domain.use_case.GetGenresUseCase
import com.example.podplay.presentation.home_podcasts.best_podcast.BestPodcastsState
import com.example.podplay.presentation.home_podcasts.genres.GenresState
import com.example.podplay.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BestPodcastsViewModel @Inject constructor(
    private val bestPodcastsUseCase: GetBestPodcastsUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val bestPodcastsMapper: UIBestPodcastsMapper,
    private val genresMapper: UIGenresMapper,
) : ViewModel() {

    private val _bestPodcastsState = MutableStateFlow(BestPodcastsState())
    val bestPodcastsState: StateFlow<BestPodcastsState> = _bestPodcastsState

    private val _genresState = MutableStateFlow(GenresState())
    val genresState: StateFlow<GenresState> = _genresState

    init {
        getBestPodcasts()
        getGenres()
    }

    private fun getBestPodcasts() {
        bestPodcastsUseCase.invoke().onEach { resource: Resource<BestPodcastsResponse> ->
            when (resource) {

                is Resource.Loading -> {
                    _bestPodcastsState.value = BestPodcastsState(isLoading = true)
                }

                is Resource.Error -> {
                    _bestPodcastsState.value = BestPodcastsState(error = resource.message.orEmpty())
                }

                is Resource.Success -> {
                    _bestPodcastsState.value =
                        BestPodcastsState(
                            data = bestPodcastsMapper.map(resource.data?.podcasts ?: listOf())
                        )
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun getGenres() {
        genresUseCase.invoke().onEach { resource: Resource<GenresResponse> ->
            when (resource) {

                is Resource.Loading -> {
                    _genresState.value = GenresState(isLoading = true)
                }

                is Resource.Error -> {
                    _genresState.value = GenresState(error = resource.message.orEmpty())
                }

                is Resource.Success -> {
                    _genresState.value =
                        GenresState(
                            data = genresMapper.map(resource.data?.genres ?: listOf())
                        )
                }
            }
        }.launchIn(viewModelScope)
    }
}