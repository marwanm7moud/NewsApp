package com.awesome.newsapp.data.local

import com.awesome.newsapp.data.local.model.ArticleEntity
import com.awesome.newsapp.data.repositoryImpl.source.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val articleDao: ArticleDao
) : LocalDataSource {
    override suspend fun insertArticles(articles: List<ArticleEntity>) {
        articleDao.insertArticles(articles)
    }

    override suspend fun getArticlesByCategory(category: String): List<ArticleEntity> =
        articleDao.getArticlesByCategory(category)


    override suspend fun deleteArticlesByCategory(category: String) {
        articleDao.deleteArticlesByCategory(category)
    }
}