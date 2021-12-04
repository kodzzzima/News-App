package com.example.latestnews.di

import com.example.latestnews.data.NewsRepository
import com.example.latestnews.data.NewsRepositoryImpl
import com.example.latestnews.data.local.NewsLocalDataSource
import com.example.latestnews.data.local.NewsLocalDataSourceImpl
import com.example.latestnews.data.remote.NewsRemoteDataSource
import com.example.latestnews.data.remote.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindNewsRepository(
        impl: NewsRepositoryImpl,
    ): NewsRepository

    @Binds
    fun bindRemoteDataSource(
        impl: NewsRemoteDataSourceImpl,
    ): NewsRemoteDataSource

    @Binds
    fun bindLocalDataSource(
        impl: NewsLocalDataSourceImpl,
    ): NewsLocalDataSource
}