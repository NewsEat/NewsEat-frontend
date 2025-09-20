package com.example.news_eat_fronted.domain.usecase.user

import com.example.news_eat_fronted.domain.entity.request.user.SetDetoxModeRequestEntity
import com.example.news_eat_fronted.domain.entity.response.user.SetDetoxModeResponseEntity
import com.example.news_eat_fronted.domain.repository.UserRepository
import javax.inject.Inject

class SetDetoxModeUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(setDetoxModeRequestEntity: SetDetoxModeRequestEntity)
    = userRepository.setDetoxMode(setDetoxModeRequestEntity)
}