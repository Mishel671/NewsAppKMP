package com.example.newsapp.presentation.newsList.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapp.core.presentation.pagination.PaginationItemUi
import com.example.newsapp.presentation.newsList.NewsListScreenAction
import com.example.newsapp.presentation.newsList.NewsListScreenUiState
import newsapp.shared.generated.resources.Res
import newsapp.shared.generated.resources.news_error_placeholder_button_refresh
import newsapp.shared.generated.resources.news_error_placeholder_title
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NewsListPaginationWidget(
    state: NewsListScreenUiState.Content,
    onAction: (NewsListScreenAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        itemsIndexed(
            items = state.paginationState.items,
            key = { _, item -> item.getKey() },
        ) { index, item ->

            LaunchedEffect(key1 = index) {
                onAction(NewsListScreenAction.OnLoadNextPageIfNeeded(index))
            }

            when (item) {
                PaginationItemUi.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            strokeWidth = 4.dp,
                        )
                    }
                }

                PaginationItemUi.Error -> {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(Res.string.news_error_placeholder_title),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = {
                                onAction(NewsListScreenAction.OnRefreshPaginationClicked)
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                            contentPadding = PaddingValues(
                                horizontal = 32.dp,
                                vertical = 12.dp
                            )
                        ) {
                            Text(
                                text = stringResource(Res.string.news_error_placeholder_button_refresh),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }

                }

                is PaginationItemUi.Item -> {
                    NewsItemWidget(
                        item = item.value,
                        onClick = { onAction(NewsListScreenAction.OnItemClicked(item.getKey())) }
                    )
                }
            }
        }
    }
}
