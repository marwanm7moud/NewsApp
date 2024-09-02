package com.awesome.newsapp.data.remote.model

data class ArticleResponse(
    val status: String?,
    val totalResults: Int?,
    val articles: List<ArticleDto>
)