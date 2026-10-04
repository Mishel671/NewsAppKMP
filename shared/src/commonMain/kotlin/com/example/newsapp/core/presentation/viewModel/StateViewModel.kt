package com.example.newsapp.core.presentation.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StateViewModel<STATE : Any, ACTION : Any, SIDE_EFFECT : Any>(
    initialState: STATE
) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    protected val _sideEffects: MutableSharedFlow<SIDE_EFFECT> = MutableSharedFlow()
    val sideEffects: SharedFlow<SIDE_EFFECT> = _sideEffects.asSharedFlow()

    abstract fun onAction(action: ACTION)
}