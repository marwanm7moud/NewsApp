package com.awesome.newsapp.data.repositoryImpl

import com.awesome.newsapp.data.local.mappers.toDomain
import com.awesome.newsapp.data.local.mappers.toEntity
import com.awesome.newsapp.data.remote.mappers.toDomain
import com.awesome.newsapp.data.repositoryImpl.source.LocalDataSource
import com.awesome.newsapp.data.repositoryImpl.source.RemoteDataSource
import com.awesome.newsapp.domain.entity.Article
import com.awesome.newsapp.domain.repository.ArticlesRepository
import com.awesome.newsapp.domain.util.NewsException
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ArticlesRepository {

    override suspend fun getArticlesByCategory(category: String): List<Article> {
        return try {
            val articles = remoteDataSource.getArticlesByCategory(category).articles
            localDataSource.deleteArticlesByCategory(category)
            localDataSource.insertArticles(articles.map { it.toEntity(category) })
            articles.map { it.toDomain() }
        } catch (exception : NewsException) {
            val cachedArticles = localDataSource.getArticlesByCategory(category)
            if (cachedArticles.isNotEmpty()) {
                return cachedArticles.map { it.toDomain() }
            } else throw exception
        }
    }
}