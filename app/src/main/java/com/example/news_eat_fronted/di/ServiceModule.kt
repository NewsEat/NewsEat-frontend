package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.data.service.AuthService
import com.example.news_eat_fronted.data.service.NewsService
import com.example.news_eat_fronted.data.service.UserService
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
    fun provideAuthService(@Anonymous retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideUserService(@Auth retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideNewsService(@Auth retrofit: Retrofit): NewsService =
        retrofit.create(NewsService::class.java)
}