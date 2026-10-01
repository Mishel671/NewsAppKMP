package com.example.newsapp.presentation.newsList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.core.Koin
import org.koin.mp.KoinPlatform.getKoin

@Composable
internal fun NewsListScreen(
    navigateToDetail: () -> Unit,
    koin: Koin = getKoin(),
    viewModel: NewsListViewModel = viewModel { koin.get() }
) {
    viewModel
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable {
                navigateToDetail()
            },
            text = "list: ${viewModel.hashCode()}"
        )
    }
}

