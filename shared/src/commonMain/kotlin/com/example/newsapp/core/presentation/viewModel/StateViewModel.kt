package com.example.newsapp.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class StateViewModel<STATE : Any, ACTION : Any, SIDE_EFFECT : Any>(
    initialState: STATE
) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    protected val _sideEffects: Channel<SIDE_EFFECT> = Channel()
    val sideEffects: Flow<SIDE_EFFECT> = _sideEffects.receiveAsFlow()

    abstract fun onAction(action: ACTION)
}