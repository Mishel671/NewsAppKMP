package com.example.newsapp.core.presentation.pagination

import androidx.compose.runtime.Immutable
import kotlin.uuid.Uuid

@Immutable
sealed class PaginationItemUi<out T> {

    data class Item<T>(
        val id: String = Uuid.random().toString(),
        val value: T,
    ) : PaginationItemUi<T>()

    data object Error : PaginationItemUi<Nothing>()
    data object Loading : PaginationItemUi<Nothing>()

    fun getKey(): String {
        return when (this) {
            is Error, is Loading -> hashCode().toString()
            is Item -> id
        }
    }

    fun getContainedValue(): T? {
        return when (this) {
            is Error, is Loading -> null
            is Item -> value
        }
    }
}
