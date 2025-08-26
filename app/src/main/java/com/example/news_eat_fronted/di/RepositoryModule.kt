package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.data.repositoryImpl.AuthRepositoryImpl
import com.example.news_eat_fronted.domain.repository.AuthRepository
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
}