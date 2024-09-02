package com.awesome.newsapp.data.local.mappers

import com.awesome.newsapp.data.local.model.ArticleEntity
import com.awesome.newsapp.data.remote.model.ArticleDto
import com.awesome.newsapp.domain.entity.Article

fun ArticleEntity.toDomain(): Article {
    return Article(
        sourceName = sourceName,
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

fun ArticleDto.toEntity(category: String): ArticleEntity {
    return ArticleEntity(
        url = this.url ?: "",
        sourceName = this.source?.name?: "",
        author = this.author?: "",
        title = this.title?: "",
        description = this.description?: "",
        urlToImage = this.urlToImage?: "",
        publishedAt = this.publishedAt?: "",
        content = this.content?: "",
        category = category
    )
}