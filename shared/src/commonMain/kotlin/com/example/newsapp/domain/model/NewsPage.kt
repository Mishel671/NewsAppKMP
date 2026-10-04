package com.example.newsapp.domain.model

internal data class NewsPage(
    val totalItems: Int,
    val news: List<NewsItem>
)