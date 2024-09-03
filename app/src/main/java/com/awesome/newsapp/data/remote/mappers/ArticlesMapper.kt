package com.awesome.newsapp.data.remote.mappers

import com.awesome.newsapp.data.remote.model.ArticleDto
import com.awesome.newsapp.domain.entity.Article

fun ArticleDto?.toDomain() = Article(
    author = this?.author ?: "UnKnown",
    content = this?.content ?: "N/A",
    description = this?.description ?: "",
    publishedAt = this?.publishedAt ?: "",
    sourceName = this?.source?.name ?: "N/A",
    title = this?.title ?: "N/A",
    url = this?.url ?: "",
    urlToImage = this?.urlToImage ?: "",
)