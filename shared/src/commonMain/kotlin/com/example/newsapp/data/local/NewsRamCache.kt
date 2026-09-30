package com.example.newsapp.data.local

import com.example.newsapp.domain.model.NewsItem

internal class NewsRamCache {

    private var newsItem: NewsItem? = null

    fun setNews(item: NewsItem) {
        newsItem = item
    }

    fun getNewsItem(): NewsItem? {
        return newsItem
    }
}