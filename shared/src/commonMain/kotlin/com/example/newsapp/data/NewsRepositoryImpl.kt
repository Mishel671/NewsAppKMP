package com.example.newsapp.data

import com.example.newsapp.data.local.NewsRamCache
import com.example.newsapp.data.remote.NewsRemote
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.model.NewsPage

internal class NewsRepositoryImpl(
    private val remote: NewsRemote,
    private val ramCache: NewsRamCache,
) : NewsRepository {

    override suspend fun getNews(query: String, page: Int): NewsPage {
        return remote.getNewsList(query, page)
    }

    override fun setNewsItemInRamCache(item: NewsItem) {
        ramCache.setNews(item)
    }

    override fun getNewsItemFromRamCache(): NewsItem? {
        return ramCache.getNewsItem()
    }
}