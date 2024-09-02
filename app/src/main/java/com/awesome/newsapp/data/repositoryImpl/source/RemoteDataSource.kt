package com.awesome.newsapp.data.repositoryImpl.source

import com.awesome.newsapp.data.remote.model.ArticleResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getArticlesByCategory(category: String): ArticleResponse
}