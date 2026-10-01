package com.example.newsapp.presentation.newsDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.presentation.newsList.NewsListViewModel
import org.koin.core.Koin
import org.koin.mp.KoinPlatform.getKoin

@Composable
internal fun NewsDetailScreen(
    koin: Koin = getKoin(),
    viewModel: NewsDetailViewModel = viewModel { koin.get() }
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("detail ${viewModel.hashCode()}")
    }
}

