package com.awesome.newsapp.presentation.screen.home

import com.awesome.newsapp.presentation.base.ErrorState

sealed interface HomeIntent{
    data class NavigateToArticleDetails(val article: ArticleUiState) : HomeIntent
    data class ChangeCurrentCategory(val category: NewsCategory) : HomeIntent
    data class LoadNewsByCategory(val category: NewsCategory) : HomeIntent
    data class ShowError(val error: ErrorState) : HomeIntent
}