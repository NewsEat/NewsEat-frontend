package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.domain.repository.AuthRepository
import com.example.news_eat_fronted.domain.usecase.auth.SendEmailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideEmailSendUseCase(authRepository: AuthRepository): SendEmailUseCase =
        SendEmailUseCase(authRepository = authRepository)
}