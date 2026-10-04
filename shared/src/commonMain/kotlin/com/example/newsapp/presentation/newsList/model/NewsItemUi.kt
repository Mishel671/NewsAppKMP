package com.example.newsapp.presentation.newsList.model

import androidx.compose.runtime.Immutable

@Immutable
internal data class NewsItemUi(
    val url: String?,
    val title: String?,
    val description: String?,
    val author: String?
)