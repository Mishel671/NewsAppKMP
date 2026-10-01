package com.example.newsapp.presentation.core.pagination

data class PaginationState<T>(
    val items: List<PaginationElement<T>> = listOf(),
    val pageIndex: Int = 1,
    val lastItemId: String? = null,
    val hasNextPage: Boolean = true,
    val isFirstPageLoaded: Boolean = false,
) {
    val offset get() = if (pageIndex == 1) 0 else items.count { it.getContainedValue() != null }
}
