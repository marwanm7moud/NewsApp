package com.awesome.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.awesome.newsapp.data.local.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}