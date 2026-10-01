package com.example.newsapp.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.nav.NewsNavPath
import com.example.newsapp.presentation.nav.newsAppNavigation

@Composable
fun AppUi() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NewsNavPath.NEWS_LIST,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        predictivePopEnterTransition = {
            EnterTransition.None
        },
        predictivePopExitTransition = {
            ExitTransition.None
        },
    ) {
        newsAppNavigation(navController)
    }
}