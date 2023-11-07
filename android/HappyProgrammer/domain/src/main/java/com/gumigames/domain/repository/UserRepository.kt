package com.gumigames.domain.repository

import com.gumigames.domain.model.basic.AuthDto
import com.gumigames.domain.model.user.LoginDto

interface UserRepository {
    suspend fun login(loginDto: LoginDto): AuthDto
}