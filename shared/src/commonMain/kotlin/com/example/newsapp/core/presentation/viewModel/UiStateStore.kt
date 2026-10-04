package com.example.newsapp.core.presentation.viewModel

import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

interface UiStateStore<T> : ReadOnlyProperty<Any, StateFlow<T>> {
    val uiState: StateFlow<T>

    override fun getValue(thisRef: Any, property: KProperty<*>): StateFlow<T> {
        return uiState
    }
}

