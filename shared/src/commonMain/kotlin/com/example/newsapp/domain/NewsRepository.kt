package com.example.newsapp.domain

import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.domain.model.NewsPage

internal interface NewsRepository {

    suspend fun getNews(query: String, page: Int): NewsPage

    fun setNewsItemInRamCache(item: NewsItem)

    fun getNewsItemFromRamCache(): NewsItem?
}