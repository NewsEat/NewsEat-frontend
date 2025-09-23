package com.example.news_eat_fronted.domain.usecase.user

import com.example.news_eat_fronted.domain.entity.request.user.UpdateCategoryRequestEntity
import com.example.news_eat_fronted.domain.repository.UserRepository

class UpdateCategoryUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(updateCategoryRequestEntity: UpdateCategoryRequestEntity): UpdateCategoryRequestEntity {
        return userRepository.updateCategories(updateCategoryRequestEntity)
    }
}
//class UpdateCategoryUseCase (private val userRepository: UserRepository) {
//    suspend operator fun invoke(updateCategoryRequestEntity: List<Int>) : UpdateCategoryRequestEntity
//    = userRepository.updateCategories(updateCategoryRequestEntity)
//}