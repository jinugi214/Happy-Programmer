package com.gumigames.domain.usecase.user

import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.domain.repository.UserRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(): UserInfoDto{
        return getValueOrThrow { userRepository.getUserInfo() }
    }
}