package com.example.newsapp.presentation.core.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class StateViewModel<STATE : Any, ACTION : Any>(initialState: STATE) : ViewModel() {

    protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val state: StateFlow<STATE> = _state.asStateFlow()

    abstract fun onAction(action: ACTION)
}