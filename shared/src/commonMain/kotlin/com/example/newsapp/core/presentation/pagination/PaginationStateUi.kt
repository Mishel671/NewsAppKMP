package com.example.newsapp.core.presentation.pagination

import androidx.compose.runtime.Immutable
import com.example.newsapp.core.domain.pagination.PaginationItem
import com.example.newsapp.core.domain.pagination.PaginationState

@Immutable
data class PaginationStateUi<T>(
    val items: List<PaginationItemUi<T>> = listOf(),
    val hasNextPage: Boolean = true,
)

fun <T, TUi> PaginationState<T>.toUi(
    convertItem: (T) -> TUi,
): PaginationStateUi<TUi> {
    return PaginationStateUi(
        items = this.items.map { item ->
            item.toPaginationElementUi(convertItem)
        },
    )
}

inline fun <T, TUi> PaginationItem<T>.toPaginationElementUi(
    convertItem: (T) -> TUi,
): PaginationItemUi<TUi> {
    return when (this) {
        is PaginationItem.Item -> {
            PaginationItemUi.Item(id, convertItem(value))
        }
        is PaginationItem.Error -> {
            PaginationItemUi.Error
        }
        is PaginationItem.Loading -> {
            PaginationItemUi.Loading
        }
    }
}