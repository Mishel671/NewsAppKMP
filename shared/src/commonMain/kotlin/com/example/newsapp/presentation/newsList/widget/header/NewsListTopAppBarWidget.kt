package com.example.newsapp.presentation.newsList.widget.header

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.newsapp.presentation.newsList.NewsListScreenUiState
import newsapp.shared.generated.resources.Res
import newsapp.shared.generated.resources.news_top_app_bar_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NewsListTopAppBarWidget(
    state: NewsListScreenUiState,
    onSearchChanged: (String) -> Unit,
    onSearchCompleted: () -> Unit,
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(Res.string.news_top_app_bar_title)
                )
            }
        )
        if (state is NewsListScreenUiState.Content) {
            NewsListSearchWidget(
                text = state.query,
                onTextChanged = onSearchChanged,
                onSearch = { onSearchCompleted() }
            )
        }
    }
}