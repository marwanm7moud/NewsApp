package com.awesome.newsapp.domain.usecase

import com.awesome.newsapp.domain.entity.Article
import com.awesome.newsapp.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
)  {
    suspend operator fun invoke(category: String): List<Article> {
        return repository.getArticlesByCategory(category)
    }
}