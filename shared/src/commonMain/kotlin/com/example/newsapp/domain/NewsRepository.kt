package com.example.newsapp.domain

import com.example.newsapp.domain.model.NewsItem

internal interface NewsRepository {

    suspend fun getNews(page: Int): List<NewsItem>

    fun setNewsItemInRamCache(item: NewsItem)

    fun getNewsItemFromRamCache(): NewsItem?
}