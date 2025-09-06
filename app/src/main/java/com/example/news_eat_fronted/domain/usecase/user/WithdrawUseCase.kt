package com.example.news_eat_fronted.domain.usecase.user

import com.example.news_eat_fronted.domain.repository.UserRepository

class WithdrawUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke()
    = userRepository.withdraw()
}