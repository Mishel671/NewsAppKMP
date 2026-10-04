package com.example.newsapp.presentation.newsList.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.presentation.newsList.NewsListScreenAction
import com.example.newsapp.presentation.newsList.NewsListScreenUiState
import com.example.newsapp.presentation.newsList.widget.header.NewsListTopAppBarWidget

@Composable
internal fun NewsListContent(
    state: NewsListScreenUiState,
    onAction: (NewsListScreenAction) -> Unit,
) {
    Scaffold(
        topBar = {
            NewsListTopAppBarWidget(
                state = state,
                onSearchChanged = {
                    onAction(NewsListScreenAction.OnSearchEntered(query = it))
                },
                onSearchCompleted = {
                    onAction(NewsListScreenAction.OnSearchCompleted)
                }
            )
        }
    ) { paddingValues ->
        val paddingModifier = Modifier.padding(paddingValues)
        when (state) {
            NewsListScreenUiState.Loading -> {
                Box(modifier = paddingModifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        strokeWidth = 4.dp,
                        modifier = Modifier.size(56.dp).align(Alignment.Center)
                    )
                }
            }

            NewsListScreenUiState.Error -> {
                NewsListErrorWidget(modifier = paddingModifier) {
                    onAction(NewsListScreenAction.OnErrorRefreshClicked)
                }
            }

            is NewsListScreenUiState.Content -> {
                PullToRefreshBox(
                    modifier = paddingModifier,
                    isRefreshing = state.isRefreshing,
                    onRefresh = { onAction(NewsListScreenAction.OnP2R) },
                ) {
                    NewsListPaginationWidget(
                        state = state,
                        onAction = onAction
                    )
                }
            }
        }
    }
}

@Preview
@Composable
internal fun NewsListContentPreview(
) {
    MaterialTheme {
        NewsListContent(
            state = NewsListPreviewMock.mockContent,
            onAction = {},
        )
    }
}


@Preview
@Composable
internal fun NewsListLoadingPreview(
) {
    MaterialTheme {
        NewsListContent(
            state = NewsListPreviewMock.mockLoading,
            onAction = {},
        )
    }
}


@Preview
@Composable
internal fun NewsListErrorPreview(
) {
    MaterialTheme {
        NewsListContent(
            state = NewsListPreviewMock.mockError,
            onAction = {},
        )
    }
}

