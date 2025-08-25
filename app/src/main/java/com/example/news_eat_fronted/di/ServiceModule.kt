package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.data.service.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideSignupService(@Anonymous retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}