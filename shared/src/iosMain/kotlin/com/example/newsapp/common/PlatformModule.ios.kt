package com.example.newsapp.common

import org.koin.dsl.module
import io.ktor.client.engine.darwin.*

actual fun platformModule() = module {
    single { Darwin.create() }
}
