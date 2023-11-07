package com.gumigames.data.repository

import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.mapper.toData
import com.gumigames.data.mapper.toDomain
import com.gumigames.data.service.UserService
import com.gumigames.data.util.handleApi
import com.gumigames.domain.model.basic.AuthDto
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.repository.UserRepository

class UserRepositoryImpl(
    private val preferenceDataSource: PreferenceDataSource,
    private val userService: UserService
): UserRepository {

    override suspend fun login(loginDto: LoginDto): AuthDto {
        return handleApi { userService.login(loginDto.toData()) }.toDomain()
    }

}