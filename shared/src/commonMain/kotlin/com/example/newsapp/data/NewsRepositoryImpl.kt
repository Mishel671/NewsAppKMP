package com.example.newsapp.data

import com.example.newsapp.data.local.NewsRamCache
import com.example.newsapp.data.remote.NewsRemote
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.NewsItem

internal class NewsRepositoryImpl(
    private val remote: NewsRemote,
    private val ramCache: NewsRamCache,
) : NewsRepository {

    override suspend fun getNews(page: Int): List<NewsItem> {
        return remote.getNewsList(page)
    }

    override fun setNewsItemInRamCache(item: NewsItem) {
        ramCache.setNews(item)
    }

    override fun getNewsItemFromRamCache(): NewsItem? {
        return ramCache.getNewsItem()
    }
}