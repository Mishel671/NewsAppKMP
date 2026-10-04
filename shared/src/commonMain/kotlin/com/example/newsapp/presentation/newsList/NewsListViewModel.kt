package com.example.newsapp.presentation.newsList

import androidx.lifecycle.viewModelScope
import com.example.newsapp.core.domain.pagination.PaginationHelper
import com.example.newsapp.core.presentation.viewModel.StateViewModel
import com.example.newsapp.core.presentation.viewModel.UiStateDelegate
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.NewsItem
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


internal class NewsListViewModel(
    private val repository: NewsRepository,
    private val converter: NewListConverter,
    private val paginationHelper: PaginationHelper<NewsItem>,
) : StateViewModel<NewsListScreenState, NewsListScreenAction, NewsListSideEffect>(
    NewsListScreenState()
) {

    val uiState by UiStateDelegate(
        converter = converter::toUiState,
        dispatcher = Dispatchers.Main.immediate
    )

    private var loadingJob: Job? = null

    init {
        loadData()
    }


    override fun onAction(action: NewsListScreenAction) {
        when (action) {
            is NewsListScreenAction.OnItemClicked -> {
                onItemClicked(action.itemId)
            }

            is NewsListScreenAction.OnLoadNextPageIfNeeded -> {
                onLoadNextPageIfNeeded(action.index)
            }

            NewsListScreenAction.OnErrorRefreshClicked -> {
                loadData(loadType = LoadType.FULL)
            }

            NewsListScreenAction.OnRefreshPaginationClicked -> {
                loadData(loadType = LoadType.PAGINATION)
            }

            NewsListScreenAction.OnSearchCompleted -> {
                onSearchComplete()
            }

            is NewsListScreenAction.OnSearchEntered -> {
                onSearchEntered(action.query)
            }

            NewsListScreenAction.OnP2R -> {
                loadData(loadType = LoadType.P2R)
            }
        }
    }

    private fun loadData(loadType: LoadType = LoadType.FULL) {
        val errorHandler = CoroutineExceptionHandler { _, _ ->
            when (loadType) {
                LoadType.FULL, LoadType.SEARCH, LoadType.P2R -> {
                    _state.update {
                        it.toErrorFullScreen()
                    }
                }

                LoadType.PAGINATION -> {
                    _state.update {
                        val paginationState =
                            paginationHelper.addError(_state.value.paginationState)
                        it.toChangePaginationState(paginationState)
                    }
                }
            }
        }

        loadingJob?.cancel()

        loadingJob = viewModelScope.launch(errorHandler) {
            when (loadType) {
                LoadType.FULL, LoadType.SEARCH -> {
                    _state.update {
                        it.toFullScreenLoader()
                    }
                }

                LoadType.P2R -> {
                    _state.update {
                        it.toP2R()
                    }
                }

                LoadType.PAGINATION -> {
                    _state.update {
                        val paginationState =
                            paginationHelper.addLoading(_state.value.paginationState)
                        it.toChangePaginationState(paginationState)
                    }
                }
            }

            val paginationState = if (loadType == LoadType.P2R) {
                paginationHelper.reset(_state.value.paginationState)
            } else {
                _state.value.paginationState
            }
            val page = repository.getNews(
                query = _state.value.query,
                page = paginationState.pageIndex
            )
            val totalCount = paginationHelper.getItemsCount(paginationState)
                .plus(page.news.size)
            val hasNextPage = totalCount < page.totalItems

            _state.update {
                it.toContent(
                    paginationState = paginationHelper.addItems(
                        paginationState = paginationState,
                        newItems = page.news,
                        hasNextPage = hasNextPage
                    )
                )
            }
        }
    }

    private fun onSearchEntered(query: String) {
        _state.update {
            it.copy(
                query = query
            )
        }
    }

    private fun onSearchComplete() {
        loadData(loadType = LoadType.SEARCH)
    }

    private fun onLoadNextPageIfNeeded(index: Int) {
        if (paginationHelper.shouldLoadNewPage(_state.value.paginationState, index)) {
            loadData(loadType = LoadType.PAGINATION)
        }
    }

    private fun onItemClicked(id: String) {
        val item = _state.value.paginationState.items.find {
            it.getKey() == id
        }?.getContainedValue() ?: return
        repository.setNewsItemInRamCache(item)
        _sideEffects.trySend(NewsListSideEffect.NavigateToDetail)

    }

    private enum class LoadType {
        FULL, P2R, SEARCH, PAGINATION
    }
}