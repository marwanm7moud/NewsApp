package com.awesome.newsapp.presentation.screens.home

import com.awesome.newsapp.domain.entity.Article

data class HomeViewState(
    val isLoading: Boolean = false,
    val currentCategory: NewsCategory = NewsCategory.BUSINESS,
    val articles: List<ArticleUiState> = emptyList(),
    val errorMessage: String? = null
)

data class ArticleUiState(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String
)

fun Article.toUIModel(): ArticleUiState {
    return ArticleUiState(
        title = title,
        content = content,
        author = author,
        description = description,
        urlToImage = urlToImage,
        url = url,
        sourceName = sourceName,
        publishedAt = publishedAt
    )
}

enum class NewsCategory(val displayName: String) {
    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology");
}