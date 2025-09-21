package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.data.datasource.AuthRemoteDataSource
import com.example.news_eat_fronted.data.datasource.BookmarkRemoteDataSource
import com.example.news_eat_fronted.data.datasource.HomeRemoteDataSource
import com.example.news_eat_fronted.data.datasource.NewsRemoteDataSource
import com.example.news_eat_fronted.data.datasource.UserRemoteDataSource
import com.example.news_eat_fronted.data.datasourceImpl.AuthRemoteDataSourceImpl
import com.example.news_eat_fronted.data.datasourceImpl.BookmarkRemoteDataSourceImpl
import com.example.news_eat_fronted.data.datasourceImpl.HomeRemoteDataSourceImpl
import com.example.news_eat_fronted.data.datasourceImpl.NewsRemoteDataSourceImpl
import com.example.news_eat_fronted.data.datasourceImpl.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindAuthRemoteDataSource (
        impl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    abstract fun bindUserRemoteDataSource (
        impl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Binds
    abstract fun bindNewsRemoteDataSource (
        impl: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource

    @Binds
    abstract fun bindBookmarkRemoteDataSource (
        impl: BookmarkRemoteDataSourceImpl
    ): BookmarkRemoteDataSource

    @Binds
    abstract fun bindHomeRemoteDataSource (
        impl: HomeRemoteDataSourceImpl
    ): HomeRemoteDataSource
}