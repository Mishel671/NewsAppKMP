package com.example.newsapp.presentation.newsDetail

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.core.presentation.subscribeOnSideEffects
import com.example.newsapp.presentation.newsDetail.widget.NewsDetailContent
import org.koin.core.Koin
import org.koin.mp.KoinPlatform.getKoin

@Composable
internal fun NewsDetailScreen(
    navigateBack: () -> Unit,
    koin: Koin = getKoin(),
    viewModel: NewsDetailViewModel = viewModel { koin.get() }
) {

    val state by viewModel.uiState.collectAsState()
    val onAction: (NewsDetailAction) -> Unit = remember { { viewModel.onAction(it) } }

    MaterialTheme {
        NewsDetailContent(
            state = state,
            onAction = onAction,
        )
    }

    viewModel.subscribeOnSideEffects {
        when (it) {
            NewsDetailSideEffect.OnBack -> {
                navigateBack()
            }
        }
    }
}

