package com.awesome.newsapp.data.repositoryImpl.source

import com.awesome.newsapp.data.local.model.ArticleEntity

interface LocalDataSource {
    suspend fun insertArticles(articles: List<ArticleEntity>)
    suspend fun getArticlesByCategory(category: String): List<ArticleEntity>
    suspend fun deleteArticlesByCategory(category: String)
}