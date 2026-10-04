package com.example.newsapp.presentation.newsList

import com.example.newsapp.core.domain.pagination.PaginationState
import com.example.newsapp.domain.model.NewsItem


internal data class NewsListScreenState(
    val mode: Mode = Mode.LOADING,
    val query: String = "",
    val isRefreshing: Boolean = false,
    val paginationState: PaginationState<NewsItem> = PaginationState()
) {

    fun toErrorFullScreen() = copy(
        mode = Mode.ERROR,
        isRefreshing = false,
        paginationState = PaginationState()
    )

    fun toFullScreenLoader() = copy(
        mode = Mode.LOADING,
        isRefreshing = false,
        paginationState = PaginationState()
    )

    fun toP2R() = copy(
        mode = Mode.CONTENT,
        isRefreshing = true,
    )

    fun toChangePaginationState(paginationState: PaginationState<NewsItem>) = copy(
        mode = Mode.CONTENT,
        paginationState = paginationState
    )


    fun toContent(paginationState: PaginationState<NewsItem>) = copy(
        mode = Mode.CONTENT,
        isRefreshing = false,
        paginationState = paginationState
    )

    enum class Mode {
        LOADING, ERROR, CONTENT
    }
}