package com.awesome.newsapp.di

import com.awesome.newsapp.data.local.LocalDataSourceImpl
import com.awesome.newsapp.data.remote.RemoteDataSourceImpl
import com.awesome.newsapp.data.repositoryImpl.source.LocalDataSource
import com.awesome.newsapp.data.repositoryImpl.source.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource
}