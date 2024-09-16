package com.example.podplay.presentation.category


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podplay.data.dto.best_podcasts.BestPodcastsResponse
import com.example.podplay.domain.mapper.UIBestPodcastsMapper
import com.example.podplay.domain.use_case.GetBestPodcastsUseCase
import com.example.podplay.util.CATEGORY_ID_ARG
import com.example.podplay.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getBestPodcastsUseCase: GetBestPodcastsUseCase,
    private val mapper: UIBestPodcastsMapper,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _categoryPodcasts = MutableStateFlow(CategoryPodcastsState(isLoading = false))
    val categoryPodcasts: StateFlow<CategoryPodcastsState> = _categoryPodcasts

    init {
        savedStateHandle.get<String>(CATEGORY_ID_ARG).also {
            it?.let { id ->
                this.onEvent(CategoryPodcastsEvent.GetPodcastsByCategory(categoryId = id))
            }
        }
    }


    private fun onEvent(event: CategoryPodcastsEvent) {
        when (event) {
            is CategoryPodcastsEvent.GetPodcastsByCategory -> {
                getPodcastsByCategory(event.categoryId)
            }
        }
    }

    private fun getPodcastsByCategory(categoryId: String) {
        getBestPodcastsUseCase.invoke(genreId = categoryId)
            .onEach { resource: Resource<BestPodcastsResponse> ->
                when (resource) {
                    is Resource.Error -> _categoryPodcasts.value =
                        CategoryPodcastsState(error = resource.message.orEmpty())

                    is Resource.Loading -> _categoryPodcasts.value =
                        CategoryPodcastsState(isLoading = true)

                    is Resource.Success -> _categoryPodcasts.value = CategoryPodcastsState(
                        data =
                        mapper.map(resource.data?.podcasts ?: listOf())
                    )
                }

            }.launchIn(viewModelScope)
    }
}