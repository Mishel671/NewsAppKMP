package com.example.newsapp.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.newsapp.core.presentation.viewModel.StateViewModel

@Composable
inline fun <SIDE_EFFECT : Any> StateViewModel<*, *, SIDE_EFFECT>.subscribeOnSideEffects(crossinline onEvent: (SIDE_EFFECT) -> Unit) {
    LaunchedEffect(Unit) {
        sideEffects.collect {
            onEvent(it)
        }
    }
}