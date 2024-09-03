package com.awesome.newsapp.data.remote.mappers

import com.awesome.newsapp.data.remote.model.ArticleDto
import com.awesome.newsapp.domain.entity.Article

fun ArticleDto?.toDomain() = Article(
    author = this?.author ?: "",
    content = this?.content ?: "",
    description = this?.description ?: "",
    publishedAt = this?.publishedAt ?: "",
    sourceName = this?.source?.name ?: "",
    title = this?.title ?: "",
    url = this?.url ?: "",
    urlToImage = this?.urlToImage ?: "",
)