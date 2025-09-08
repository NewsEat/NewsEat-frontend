package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.data.repositoryImpl.AuthRepositoryImpl
import com.example.news_eat_fronted.data.repositoryImpl.NewsRepositoryImpl
import com.example.news_eat_fronted.data.repositoryImpl.UserRepositoryImpl
import com.example.news_eat_fronted.domain.repository.AuthRepository
import com.example.news_eat_fronted.domain.repository.NewsRepository
import com.example.news_eat_fronted.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository
}