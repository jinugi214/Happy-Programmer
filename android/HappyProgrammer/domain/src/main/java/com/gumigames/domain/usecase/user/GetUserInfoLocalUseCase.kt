package com.gumigames.domain.usecase.user

import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): UserInfoDto{
        return userRepository.getUserInfoLocal()
    }
}