package com.example.news_eat_fronted.domain.usecase.user

import com.example.news_eat_fronted.domain.entity.request.user.UpdateNicknameRequestEntity
import com.example.news_eat_fronted.domain.repository.UserRepository
import javax.inject.Inject

class UpdateNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(updateNicknameRequestEntity: UpdateNicknameRequestEntity)
    = userRepository.updateNickname(updateNicknameRequestEntity)
}