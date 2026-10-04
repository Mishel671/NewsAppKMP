package com.example.newsapp.presentation.newsDetail.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import newsapp.shared.generated.resources.Res
import newsapp.shared.generated.resources.ic_back
import newsapp.shared.generated.resources.ic_extended_article
import newsapp.shared.generated.resources.news_top_app_bar_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun NewsDetailAppBarWidget(
    link: String?,
    onBackClicked: () -> Unit,
    onArticleClicked: (String) -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(Res.string.news_top_app_bar_title)
            )
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.padding(end = 10.dp),
                shape = IconButtonDefaults.outlinedShape,
                onClick = onBackClicked
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_back),
                    contentDescription = null,
                )
            }
        },
        actions = {
            if (link == null) return@TopAppBar
            IconButton(
                shape = IconButtonDefaults.outlinedShape,
                onClick = { onArticleClicked(link) }
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_extended_article),
                    contentDescription = null,
                )
            }
        }
    )

}