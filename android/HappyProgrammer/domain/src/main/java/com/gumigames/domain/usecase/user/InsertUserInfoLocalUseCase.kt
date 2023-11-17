package com.gumigames.domain.usecase.user

import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserInfoLocalUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(userInfoDto: UserInfoDto){
        userRepository.insertUserInfoLocal(userInfoDto)
    }
}