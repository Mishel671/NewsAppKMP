package com.example.newsapp.common.platform

import org.koin.dsl.module
import io.ktor.client.engine.darwin.*

actual fun platformModule() = module {
    single { Darwin.create() }
    single<BrowserHelper> { BrowserHelper() }
}
