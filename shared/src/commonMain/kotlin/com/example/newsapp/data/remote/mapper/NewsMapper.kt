package com.example.newsapp.data.remote.mapper

import com.example.newsapp.data.remote.model.NewsItemDto
import com.example.newsapp.data.remote.model.NewsResponse
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.model.NewsPage
import com.example.newsapp.domain.model.Source
import kotlin.uuid.Uuid

internal class NewsMapper {

    fun map(dto: NewsResponse): NewsPage {
        return NewsPage(
            totalItems = dto.totalResults,
            news = dto.articles.map {
                map(it)
            }
        )
    }

    private fun map(dto: NewsItemDto): NewsItem {
        return NewsItem(
            uniqId = Uuid.random().toString(),
            author = dto.author,
            content = dto.content,
            description = dto.description,
            publishedAt = dto.publishedAt,
            source = Source(id = dto.source?.id, name = dto.source?.name),
            title = dto.title,
            url = dto.url,
            urlToImage = dto.urlToImage
        )
    }
}