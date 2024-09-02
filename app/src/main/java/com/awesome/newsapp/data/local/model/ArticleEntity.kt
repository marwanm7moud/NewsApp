package com.awesome.newsapp.data.local.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val url: String,
    val sourceName: String,
    val author: String,
    val title: String,
    val category: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)