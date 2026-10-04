package com.example.newsapp.presentation.newsDetail

import com.example.newsapp.common.platform.AppLogger
import com.example.newsapp.common.platform.BrowserHelper
import com.example.newsapp.core.presentation.viewModel.StateViewModel
import com.example.newsapp.core.presentation.viewModel.UiStateDelegate
import com.example.newsapp.domain.NewsRepository
import kotlinx.coroutines.flow.update

internal class NewsDetailViewModel(
    private val converter: NewsDetailConverter,
    private val repository: NewsRepository,
    private val browserHelper: BrowserHelper,
) : StateViewModel<NewsDetailScreenState, NewsDetailAction, NewsDetailSideEffect>(
    NewsDetailScreenState()
) {

    val uiState by UiStateDelegate(converter = converter::toUiState)

    init {
        initScreen()
    }

    override fun onAction(action: NewsDetailAction) {
        when (action) {
            NewsDetailAction.OnBackClicked -> {
                onBackClicked()
            }

            is NewsDetailAction.OnOpenLink -> {
                onBrowserClicked(action.link)
            }
        }
    }

    private fun initScreen() {
        val item = repository.getNewsItemFromRamCache() ?: return
        _state.update {
            it.copy(
                detail = item
            )
        }
    }

    private fun onBrowserClicked(link: String) {
        browserHelper.launchUrlInBrowser(link)
    }

    private fun onBackClicked() {
        _sideEffects.trySend(NewsDetailSideEffect.OnBack)
    }

    override fun onCleared() {
        super.onCleared()
        AppLogger.d("MainLog", "OnClear")
    }
}