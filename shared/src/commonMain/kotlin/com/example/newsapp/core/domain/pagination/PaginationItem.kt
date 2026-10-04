package com.example.newsapp.core.domain.pagination

import androidx.compose.runtime.Immutable
import kotlin.uuid.Uuid

@Immutable
sealed class PaginationItem<out T> {

    data class Item<T>(val value: T) : PaginationItem<T>() {
        var id: String = Uuid.random().toString()
            private set
    }


    data object Error : PaginationItem<Nothing>()

    data object Loading : PaginationItem<Nothing>()

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