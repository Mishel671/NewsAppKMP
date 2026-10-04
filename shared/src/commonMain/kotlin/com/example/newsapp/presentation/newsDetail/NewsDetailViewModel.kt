package com.example.newsapp.presentation.newsDetail

import com.example.newsapp.core.presentation.viewModel.StateViewModel
import com.example.newsapp.common.platform.AppLogger

class NewsDetailViewModel : StateViewModel<Unit, Unit, Unit>(Unit) {

    init {
        AppLogger.d("MainLog", "Viewmodel detail init ${this.hashCode()}")
    }

    override fun onAction(action: Unit) {
        TODO("Not yet implemented")
    }
}