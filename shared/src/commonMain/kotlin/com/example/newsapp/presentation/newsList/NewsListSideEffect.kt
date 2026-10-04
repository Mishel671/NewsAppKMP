package com.example.newsapp.presentation.newsList

sealed interface NewsListSideEffect {

    data object NavigateToDetail: NewsListSideEffect
}