package com.awesome.newsapp.presentation.screens.home

sealed class HomeIntent{
    data class NavigateToArticleDetails(val article: ArticleUiState) : HomeIntent()
    data class ChangeCurrentCategory(val category: NewsCategory) : HomeIntent()
    data class LoadNewsByCategory(val category: NewsCategory) : HomeIntent()
    data class ShowError(val message: String) : HomeIntent()
}