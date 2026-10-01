package com.example.newsapp.presentation.core.viewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.job

fun <ScreenState : Any, UiState> StateViewModel<ScreenState, *>.UiStateDelegate(
    converter: (ScreenState) -> UiState,
): UiStateStore<UiState> {
    val childJob = Job(viewModelScope.coroutineContext.job)
    val newScope = CoroutineScope(childJob + Dispatchers.IO)
    return UiStateStoreImpl(
        converter = converter,
        stateFlow = state,
        scope = newScope,
    )
}

private class UiStateStoreImpl<ScreenState, UiState>(
    converter: (ScreenState) -> UiState,
    stateFlow: StateFlow<ScreenState>,
    scope: CoroutineScope,
) : UiStateStore<UiState> {

    override val uiState: StateFlow<UiState> =
        stateFlow
            .map(converter)
            .stateIn(scope, SharingStarted.Eagerly, converter(stateFlow.value))
}
