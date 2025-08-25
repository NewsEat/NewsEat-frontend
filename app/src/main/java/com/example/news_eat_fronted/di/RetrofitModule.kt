package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.BuildConfig
import com.example.news_eat_fronted.data.interceptor.AuthAuthenticator
import com.example.news_eat_fronted.data.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    // 공통 OkHttpClient 빌더 함수
    private fun createOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor? = null,
        authenticator: AuthAuthenticator? = null
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)

        authInterceptor?.let { builder.addInterceptor(it) }
        authenticator?.let { builder.authenticator(it) }

        return builder.build()
    }

    // 인증 없는 OkHttpClient
    @Provides
    @Anonymous
    @Singleton
    fun provideAnonymousOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = createOkHttpClient(loggingInterceptor)

    // 인증 있는 OkHttpClient
    @Provides
    @Auth
    @Singleton
    fun provideAuthOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        authenticator: AuthAuthenticator
    ): OkHttpClient = createOkHttpClient(
        loggingInterceptor,
        authInterceptor,
        authenticator
    )

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Anonymous
    @Singleton
    fun provideAnonymousRetrofit(@Anonymous okHttpClient: OkHttpClient): Retrofit =
        createRetrofit(okHttpClient)

    @Provides
    @Auth
    @Singleton
    fun provideAuthRetrofit(@Auth okHttpClient: OkHttpClient): Retrofit =
        createRetrofit(okHttpClient)
}