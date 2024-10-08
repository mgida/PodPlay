package com.example.podplay.presentation.podcast_details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podplay.data.dto.podcast_details.PodcastDetailsResponse
import com.example.podplay.domain.mapper.UIPodcastDetailsMapper
import com.example.podplay.domain.use_case.GetPodcastDetailsUseCase
import com.example.podplay.presentation.podcast_details.PodcastDetailsState
import com.example.podplay.util.PODCAST_ID_ARG
import com.example.podplay.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PodcastDetailsViewModel @Inject constructor(
    private val getPodcastDetailsUseCase: GetPodcastDetailsUseCase,
    private val uiPodcastDetailsMapper: UIPodcastDetailsMapper,
    savedStateHandle: SavedStateHandle

) : ViewModel() {

    private val _podcastDetailsState = MutableStateFlow(PodcastDetailsState())
    val podcastDetailsState: StateFlow<PodcastDetailsState> = _podcastDetailsState

    init {
        savedStateHandle.get<String>(PODCAST_ID_ARG).also { podcastId ->
            podcastId?.let {
                getPodcastDetails(podcastId = it)
            }
        }
    }

    private fun getPodcastDetails(podcastId: String) {
        getPodcastDetailsUseCase.invoke(podcastId = podcastId)
            .onEach { resource: Resource<PodcastDetailsResponse> ->
                when (resource) {

                    is Resource.Loading -> {
                        _podcastDetailsState.value = PodcastDetailsState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _podcastDetailsState.value =
                            PodcastDetailsState(error = resource.message ?: "")
                    }

                    is Resource.Success -> {
                        resource.data?.let {
                            _podcastDetailsState.value =
                                PodcastDetailsState(
                                    data = uiPodcastDetailsMapper.map(it)
                                )
                        }
                    }
                }

            }.launchIn(viewModelScope)
    }

}