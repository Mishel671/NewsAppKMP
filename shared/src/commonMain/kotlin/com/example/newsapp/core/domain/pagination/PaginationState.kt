package com.example.newsapp.core.domain.pagination

data class PaginationState<T>(
    val items: List<PaginationItem<T>> = listOf(),
    val pageIndex: Int = 1,
    val hasNextPage: Boolean = true,
    val isFirstPageLoaded: Boolean = false,
)
