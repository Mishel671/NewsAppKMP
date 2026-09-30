package com.example.newsapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
internal data class NewsResponse(

    @SerialName("articles")
    val articles: List<NewsItemDto>,
)
