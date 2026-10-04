package com.example.newsapp.presentation.newsList

import com.example.newsapp.core.presentation.pagination.toUi
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.presentation.newsList.model.NewsItemUi

internal class NewListConverter {

    fun toUiState(state: NewsListScreenState): NewsListScreenUiState {
        return when (state.mode) {
            NewsListScreenState.Mode.LOADING -> {
                NewsListScreenUiState.Loading
            }

            NewsListScreenState.Mode.ERROR -> {
                NewsListScreenUiState.Error
            }

            NewsListScreenState.Mode.CONTENT -> {
                NewsListScreenUiState.Content(
                    query = state.query,
                    isRefreshing = state.isRefreshing,
                    paginationState = state.paginationState.toUi { toUi(it) }
                )
            }
        }
    }

    private fun toUi(item: NewsItem): NewsItemUi {
        return NewsItemUi(
            url = item.urlToImage,
            title = item.title,
            description = item.description,
            author = item.author
        )
    }
}