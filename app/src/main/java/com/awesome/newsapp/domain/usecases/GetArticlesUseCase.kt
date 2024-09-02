package com.awesome.newsapp.domain.usecases

import com.awesome.newsapp.domain.entity.Article
import com.awesome.newsapp.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
)  {
    suspend fun getArticlesByCategory(category: String): List<Article> {
        return repository.getArticlesByCategory(category)
    }
}