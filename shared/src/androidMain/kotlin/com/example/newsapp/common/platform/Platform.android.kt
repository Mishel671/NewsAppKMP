package com.example.newsapp.common.platform

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun platformModule() = module {
    single<HttpClientEngine> { Android.create() }
    single<BrowserHelper> { BrowserHelper (androidContext())}
}