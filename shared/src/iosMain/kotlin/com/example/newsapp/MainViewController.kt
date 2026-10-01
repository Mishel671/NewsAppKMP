package com.example.newsapp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.newsapp.presentation.AppUi
import org.koin.core.KoinApplication

fun MainViewController() = ComposeUIViewController {
    AppUi()
}