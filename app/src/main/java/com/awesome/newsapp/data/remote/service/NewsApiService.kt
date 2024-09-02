package com.awesome.newsapp.data.remote.service

import com.awesome.newsapp.data.remote.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApiService {
    @GET("top-headlines/category/{category}/us.json")
    suspend fun getArticlesByCategory(
        @Path("category") category: String
    ): Response<ArticleResponse>
}