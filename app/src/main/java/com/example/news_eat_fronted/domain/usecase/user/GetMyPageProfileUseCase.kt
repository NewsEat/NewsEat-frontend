package com.example.news_eat_fronted.domain.usecase.user

import com.example.news_eat_fronted.domain.entity.response.user.GetMyPageProfileResponseEntity
import com.example.news_eat_fronted.domain.repository.UserRepository
import javax.inject.Inject

class GetMyPageProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): GetMyPageProfileResponseEntity {
        return userRepository.getMyPageProfile()
    }
}
