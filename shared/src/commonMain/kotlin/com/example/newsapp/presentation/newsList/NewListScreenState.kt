package com.example.newsapp.presentation.newsList

internal data class NewListScreenState(
    val mode: String
) {

    sealed interface Mode {
        data object Loading : Mode

        data object Error : Mode

        data class Content(
            val list: String
        ) : Mode
    }
}