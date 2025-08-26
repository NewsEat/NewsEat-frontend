package com.example.news_eat_fronted.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Anonymous

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Auth