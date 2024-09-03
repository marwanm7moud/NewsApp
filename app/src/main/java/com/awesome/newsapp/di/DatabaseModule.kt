package com.awesome.newsapp.di

import android.content.Context
import androidx.room.Room
import com.awesome.newsapp.data.local.dao.ArticleDao
import com.awesome.newsapp.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DB_NAME = "NEWS_DB"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideArticleDao(db: NewsDatabase): ArticleDao {
        return db.articleDao()
    }
}