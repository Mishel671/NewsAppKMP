package com.example.newsapp.presentation.newsList

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.presentation.newsList.widget.NewsListContent
import org.koin.core.Koin
import org.koin.mp.KoinPlatform.getKoin

@Composable
internal fun NewsListScreen(
    navigateToDetail: () -> Unit,
    koin: Koin = getKoin(),
    viewModel: NewsListViewModel = viewModel { koin.get() }
) {
    val state by viewModel.uiState.collectAsState()
    val onAction: (NewsListScreenAction) -> Unit = remember { { viewModel.onAction(it) } }

    MaterialTheme {
        NewsListContent(
            state = state,
            onAction = onAction,
        )
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect {
            when (it) {
                is NewsListSideEffect.NavigateToDetail -> {
                    navigateToDetail()
                }
            }
        }
    }
}
