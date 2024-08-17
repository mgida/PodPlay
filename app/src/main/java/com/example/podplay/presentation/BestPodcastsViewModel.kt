package com.example.podplay.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.domain.mapper.UIBestPodcastsMapper
import com.example.podplay.domain.use_case.GetBestPodcastsUseCase
import com.example.podplay.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BestPodcastsViewModel @Inject constructor(
    private val useCase: GetBestPodcastsUseCase,
    private val mapper: UIBestPodcastsMapper
) : ViewModel() {

    private val _bestPodcastsState = MutableStateFlow(BestPodcastsState())
    val bestPodcastsState: StateFlow<BestPodcastsState> = _bestPodcastsState

    init {
        getBestPodcasts()
    }

    private fun getBestPodcasts() {
        useCase.invoke().onEach { resource: Resource<BestPodcastsResponse> ->
            when (resource) {

                is Resource.Loading -> {
                    _bestPodcastsState.value = BestPodcastsState(isLoading = true)
                }

                is Resource.Error -> {
                    _bestPodcastsState.value = BestPodcastsState(error = resource.message ?: "")
                }

                is Resource.Success -> {
                    _bestPodcastsState.value =
                        BestPodcastsState(
                            data = mapper.map(resource.data?.podcasts ?: listOf())
                        )
                }
            }

        }.launchIn(viewModelScope)
    }
}