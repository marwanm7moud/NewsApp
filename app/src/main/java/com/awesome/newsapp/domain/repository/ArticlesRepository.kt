package com.awesome.newsapp.domain.repository

import com.awesome.newsapp.domain.entity.Article

interface ArticlesRepository {
    suspend fun getArticlesByCategory(
        category: String
    ): List<Article>
}