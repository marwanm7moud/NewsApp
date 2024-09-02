package com.awesome.newsapp.presentation.navigation

import com.awesome.newsapp.presentation.screens.home.ArticleUiState
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenNavigator

@Serializable
data class ArticleDetailsScreenNavigator(
    val article: ArticleUiState
)