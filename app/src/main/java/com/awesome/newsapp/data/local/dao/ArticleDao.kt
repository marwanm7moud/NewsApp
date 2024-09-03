package com.awesome.newsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.awesome.newsapp.data.local.model.ArticleEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles WHERE category = :category")
    suspend fun getArticlesByCategory(category: String): List<ArticleEntity>

    @Query("DELETE FROM articles WHERE category = :category")
    suspend fun deleteArticlesByCategory(category: String)
}