package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.domain.repository.AuthRepository
import com.example.news_eat_fronted.domain.usecase.auth.CheckEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.SendEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.SignupUseCase
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

    @Provides
    @Singleton
    fun provideCheckEmailUseCase(authRepository: AuthRepository): CheckEmailUseCase =
        CheckEmailUseCase(authRepository = authRepository)

    @Provides
    @Singleton
    fun provideSignupUseCase(authRepository: AuthRepository): SignupUseCase =
        SignupUseCase(authRepository = authRepository)
}