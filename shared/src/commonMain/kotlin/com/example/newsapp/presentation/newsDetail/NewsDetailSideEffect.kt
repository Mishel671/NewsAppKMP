package com.example.newsapp.presentation.newsDetail

internal sealed interface NewsDetailSideEffect {

    data object OnBack: NewsDetailSideEffect
}