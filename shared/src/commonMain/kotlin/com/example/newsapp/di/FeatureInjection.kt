package com.example.newsapp.di

import com.example.newsapp.core.domain.pagination.PaginationHelper
import com.example.newsapp.data.NewsRepositoryImpl
import com.example.newsapp.data.local.NewsRamCache
import com.example.newsapp.data.remote.NewsRemote
import com.example.newsapp.data.remote.mapper.NewsMapper
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.presentation.newsDetail.NewsDetailConverter
import com.example.newsapp.presentation.newsDetail.NewsDetailViewModel
import com.example.newsapp.presentation.newsList.NewListConverter
import com.example.newsapp.presentation.newsList.NewsListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal fun newsFeatureModule() = module {
    single<NewsMapper> { NewsMapper() }
    single<NewsRamCache> { NewsRamCache() }
    single<NewsRemote> { NewsRemote(get(), get()) }
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }

    newsListModule()
    newsDetailModule()
}

private fun Module.newsListModule() {
    single<NewListConverter> { NewListConverter() }
    single<PaginationHelper<NewsItem>> { PaginationHelper() }
    factoryOf(::NewsListViewModel)
}

private fun Module.newsDetailModule() {
    single<NewsDetailConverter> { NewsDetailConverter() }
    factoryOf(::NewsDetailViewModel)
}
