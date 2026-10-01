package com.example.newsapp.data.remote.mapper

import com.example.newsapp.data.remote.model.NewsItemDto
import com.example.newsapp.data.remote.model.NewsResponse
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.model.Source
import kotlin.uuid.Uuid

internal class NewsMapper {

    fun map(dto: NewsResponse): List<NewsItem> {
        return dto.articles.map {
            NewsItem(
                uniqId = Uuid.random().toString(),
                author = it.author,
                content = it.content,
                description = it.description,
                publishedAt = it.publishedAt,
                source = Source(id = it.source.id, name = it.source.name),
                title = it.title,
                url = it.url,
                urlToImage = it.urlToImage
            )
        }
    }
}