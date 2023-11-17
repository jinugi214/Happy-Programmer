package com.gumigames.domain.usecase.user

import com.gumigames.domain.model.basic.AuthDto
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.repository.UserRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(loginDto: LoginDto): AuthDto{
        return getValueOrThrow { userRepository.login(loginDto) }
    }
}