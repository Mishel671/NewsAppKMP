package com.example.newsapp.presentation.newsDetail

import androidx.compose.runtime.Immutable

@Immutable
internal data class NewsDetailUiState(
    val title: String? = null,
    val description: String? = null,
    val content: String? = null,
    val author: String? = null,
    val publishedAt: String? = null,
    val sourceName: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
)