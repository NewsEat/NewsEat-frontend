package com.example.news_eat_fronted.di

import com.example.news_eat_fronted.domain.repository.AuthRepository
import com.example.news_eat_fronted.domain.repository.UserRepository
import com.example.news_eat_fronted.domain.usecase.auth.CheckEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.LoginUseCase
import com.example.news_eat_fronted.domain.usecase.auth.ReissueTokenUseCase
import com.example.news_eat_fronted.domain.usecase.auth.SendEmailUseCase
import com.example.news_eat_fronted.domain.usecase.auth.SignupUseCase
import com.example.news_eat_fronted.domain.usecase.user.GetMyPageProfileUseCase
import com.example.news_eat_fronted.domain.usecase.user.UpdateNicknameUseCase
import com.example.news_eat_fronted.domain.usecase.user.WithdrawUseCase
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

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase =
        LoginUseCase(authRepository = authRepository)

    @Provides
    @Singleton
    fun provideReissueToken(authRepository: AuthRepository): ReissueTokenUseCase =
        ReissueTokenUseCase(authRepository = authRepository)

    @Provides
    @Singleton
    fun provideWithdraw(userRepository: UserRepository): WithdrawUseCase =
        WithdrawUseCase(userRepository = userRepository)

    @Provides
    @Singleton
    fun provideGetMyPageProfileUseCase(userRepository: UserRepository): GetMyPageProfileUseCase =
        GetMyPageProfileUseCase(userRepository = userRepository)

    @Provides
    @Singleton
    fun provideUpdateNickname(userRepository: UserRepository): UpdateNicknameUseCase =
        UpdateNicknameUseCase(userRepository = userRepository)
}