package com.example.newsapp.presentation.newsDetail

internal sealed interface NewsDetailAction {

    data class OnOpenLink(val link: String): NewsDetailAction

    data object OnBackClicked: NewsDetailAction
}