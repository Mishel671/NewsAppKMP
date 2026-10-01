package com.example.newsapp.data.remote

import com.example.newsapp.data.remote.mapper.NewsMapper
import com.example.newsapp.data.remote.model.NewsResponse
import com.example.newsapp.domain.model.NewsItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters

internal class NewsRemote(
    private val client: HttpClient,
    private val mapper: NewsMapper
) {

    suspend fun getNewsList(page: Int): List<NewsItem> {
        val response = client.get(NewsEndpoints.TOP_HEADLINES) {
            url {
                with(parameters) {
                    append("category", "technology")
                    append("pageSize", "20")
                    append("page", page.toString())
                }
            }
        }
        return mapper.map(response.body<NewsResponse>())
    }
}