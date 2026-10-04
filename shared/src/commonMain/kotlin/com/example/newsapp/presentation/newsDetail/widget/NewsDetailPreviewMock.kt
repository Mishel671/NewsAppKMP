package com.example.newsapp.presentation.newsDetail.widget

import com.example.newsapp.presentation.newsDetail.NewsDetailUiState

internal object NewsDetailPreviewMock {

    val mock = NewsDetailUiState(
        title = "Новость",
        description = "Неожиданная новость",
        content = "Произошла новость",
        author = "Райан Гослинг",
        publishedAt = "2026-10-02T21:19:05Z",
        sourceName = "Источник",
        url = "",
        urlToImage = ""
    )
}