package com.awesome.newsapp.data.remote

import com.awesome.newsapp.data.remote.model.ArticleResponse
import com.awesome.newsapp.data.remote.service.NewsApiService
import com.awesome.newsapp.data.repositoryImpl.source.RemoteDataSource
import com.awesome.newsapp.domain.util.EmptyDataException
import com.awesome.newsapp.domain.util.NetworkException
import com.awesome.newsapp.domain.util.ServerErrorException
import com.awesome.newsapp.domain.util.UnknownErrorException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RetrofitDataSource @Inject constructor(
    private val apiService: NewsApiService
) : RemoteDataSource {
    override suspend fun getArticlesByCategory(category: String): ArticleResponse {
        return wrapApiResponse { apiService.getArticlesByCategory(category) }
    }

    private suspend fun <T> wrapApiResponse(
        request: suspend () -> Response<T>
    ): T {
        try {
            val response = request()
            return if (response.isSuccessful) {
                response.body() ?: throw EmptyDataException("No data")
            } else {
                throw when (response.code()) {
                    500 -> ServerErrorException(response.message())
                    else -> UnknownErrorException(response.message())
                }
            }
        } catch (io: IOException) {
            throw NetworkException(io.message)
        }
    }
}