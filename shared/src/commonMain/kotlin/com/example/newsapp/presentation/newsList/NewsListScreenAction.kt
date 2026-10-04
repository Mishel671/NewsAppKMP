package com.example.newsapp.presentation.newsList

internal sealed interface NewsListScreenAction {
    data object OnErrorRefreshClicked : NewsListScreenAction
    data object OnRefreshPaginationClicked : NewsListScreenAction
    data class OnItemClicked(val itemId: String) : NewsListScreenAction
    data class OnLoadNextPageIfNeeded(val index: Int) : NewsListScreenAction
    data class OnSearchEntered(val query: String) : NewsListScreenAction
    data object OnSearchCompleted : NewsListScreenAction
    data object OnP2R : NewsListScreenAction

}