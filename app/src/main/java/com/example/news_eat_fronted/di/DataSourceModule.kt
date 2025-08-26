package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.data.datasource.AuthRemoteDataSource
import com.example.news_eat_fronted.data.datasourceImpl.AuthRemoteDataSourceImpl
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
}