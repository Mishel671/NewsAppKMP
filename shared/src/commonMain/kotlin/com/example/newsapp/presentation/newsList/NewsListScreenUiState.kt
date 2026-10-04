package com.example.newsapp.presentation.newsList

import androidx.compose.runtime.Immutable
import com.example.newsapp.core.presentation.pagination.PaginationStateUi
import com.example.newsapp.presentation.newsList.model.NewsItemUi

@Immutable
internal sealed interface NewsListScreenUiState {

    data object Loading : NewsListScreenUiState

    data object Error : NewsListScreenUiState

    data class Content(
        val query: String,
        val isRefreshing: Boolean,
        val paginationState: PaginationStateUi<NewsItemUi>
    ) : NewsListScreenUiState
}