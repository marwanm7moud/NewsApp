package com.awesome.newsapp.presentation.navigation

import com.awesome.newsapp.presentation.screen.home.ArticleUiState
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenNavigator

@Serializable
data class ArticleDetailsScreenNavigator(
    val article: ArticleUiState
)