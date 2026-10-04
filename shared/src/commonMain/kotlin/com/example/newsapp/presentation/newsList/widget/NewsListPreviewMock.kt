package com.example.newsapp.presentation.newsList.widget

import com.example.newsapp.core.presentation.pagination.PaginationItemUi
import com.example.newsapp.core.presentation.pagination.PaginationStateUi
import com.example.newsapp.presentation.newsList.NewsListScreenUiState
import com.example.newsapp.presentation.newsList.model.NewsItemUi

internal object NewsListPreviewMock {

    val mockContent = NewsListScreenUiState.Content(
        query = "",
        isRefreshing = false,
        paginationState = PaginationStateUi(
            items = listOf(
                PaginationItemUi.Item(
                    value = NewsItemUi(
                        url = "",
                        title = "Заголовок",
                        description = "Описание",
                        author = "Райан Гослинг"
                    )
                ),
                PaginationItemUi.Item(
                    value = NewsItemUi(
                        url = "",
                        title = "Заголовок 2",
                        description = "Описание 2",
                        author = "Райан Гослинг"
                    )
                ),
                PaginationItemUi.Error,
                PaginationItemUi.Loading
            )
        )
    )

    val mockError = NewsListScreenUiState.Error

    val mockLoading = NewsListScreenUiState.Loading
}