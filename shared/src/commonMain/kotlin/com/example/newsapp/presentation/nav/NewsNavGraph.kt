package com.example.newsapp.presentation.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.newsapp.presentation.newsDetail.NewsDetailScreen
import com.example.newsapp.presentation.newsList.NewsListScreen

internal fun NavGraphBuilder.newsAppNavigation(navController: NavHostController) {
    newsListDestination(navController)
    newsDetailDestination(navController)
}

private fun NavGraphBuilder.newsListDestination(navController: NavHostController) {
    composable(NewsNavPath.NEWS_LIST) {
        NewsListScreen(
            navigateToDetail = { navController.navigate(NewsNavPath.NEWS_DETAIL) }
        )
    }
}

private fun NavGraphBuilder.newsDetailDestination(navController: NavHostController) {
    composable(NewsNavPath.NEWS_DETAIL) {
        NewsDetailScreen()
    }
}