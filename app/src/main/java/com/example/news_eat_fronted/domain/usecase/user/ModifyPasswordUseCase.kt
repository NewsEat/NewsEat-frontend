package com.example.news_eat_fronted.domain.usecase.user

import com.example.news_eat_fronted.domain.entity.request.user.ModifyPwRequestEntity
import com.example.news_eat_fronted.domain.repository.UserRepository
import javax.inject.Inject

class ModifyPasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(modifyPwRequestEntity: ModifyPwRequestEntity)
    = userRepository.modifyPassword(modifyPwRequestEntity)
}