package com.example.newsapp.presentation.newsList

import androidx.lifecycle.viewModelScope
import com.example.newsapp.presentation.core.viewModel.StateViewModel
import com.example.newsapp.presentation.core.viewModel.UiStateDelegate
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.launch


internal class NewsListViewModel(
    private val repository: NewsRepository,
    private val converter: NewListConverter,
): StateViewModel<Unit, Unit>(Unit) {

    val uiState by UiStateDelegate(converter = converter::toUiState)


    init {
        viewModelScope.launch {
            repository.getNews(page = 1)
        }
    }


    override fun onAction(action: Unit) {
        TODO("Not yet implemented")
    }
}