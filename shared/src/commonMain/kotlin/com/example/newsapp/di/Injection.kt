package com.example.newsapp.di

import com.example.newsapp.common.platform.platformModule
import com.example.newsapp.data.NewsRepositoryImpl
import com.example.newsapp.data.local.NewsRamCache
import com.example.newsapp.data.remote.NewsRemote
import com.example.newsapp.data.remote.mapper.NewsMapper
import com.example.newsapp.domain.NewsRepository
import com.example.newsapp.presentation.newsDetail.NewsDetailViewModel
import com.example.newsapp.presentation.newsList.NewsListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = true, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            commonModule(enableNetworkLogs = enableNetworkLogs),
            viewModelModule(),
            platformModule()
        )
    }

private fun commonModule(enableNetworkLogs: Boolean) = module {
    single { createJson() }
    single {  createHttpClient(get(), get(), enableNetworkLogs = enableNetworkLogs) }

    single { CoroutineScope(Dispatchers.Default + SupervisorJob()) }

    single<NewsMapper> { NewsMapper() }
    single<NewsRamCache> { NewsRamCache() }
    single<NewsRemote> { NewsRemote(get(), get()) }
    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}


private fun viewModelModule() = module {
    factoryOf(::NewsListViewModel)
    factoryOf(::NewsDetailViewModel)
}

private fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

private fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    json: Json,
    enableNetworkLogs: Boolean
) = HttpClient(httpClientEngine) {
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            // Вынести в local
            host = "newsapi.org"
            parameters.append("apiKey","d30c039132104c7f9e98fdaf39bbaec2")
        }
    }
    install(ContentNegotiation) {
        json(json)
    }
    if (enableNetworkLogs) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
    }
}