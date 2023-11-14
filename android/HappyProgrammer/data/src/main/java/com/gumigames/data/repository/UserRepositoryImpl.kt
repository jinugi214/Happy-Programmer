package com.gumigames.data.repository

import com.gumigames.data.datasource.dao.UserDao
import com.gumigames.data.datasource.sharedpreference.PreferenceDataSource
import com.gumigames.data.mapper.toData
import com.gumigames.data.mapper.toDomain
import com.gumigames.data.service.UserService
import com.gumigames.data.util.handleApi
import com.gumigames.domain.model.basic.AuthDto
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.domain.repository.UserRepository

class UserRepositoryImpl(
    private val preferenceDataSource: PreferenceDataSource,
    private val userService: UserService,
    private val userDao: UserDao
): UserRepository {

    override suspend fun login(loginDto: LoginDto): AuthDto {
        return handleApi { userService.login(loginDto.toData()) }.toDomain()
    }

    /**
     * 사용자 정보 조회
     */
    override suspend fun getUserInfo(): UserInfoDto {
        return handleApi { userService.getUserInfo() }.toDomain(preferenceDataSource)
    }

    /**
     * 사용자 정보 로컬 조회
     */
    override suspend fun getUserInfoLocal(): UserInfoDto {
        return userDao.getUserInfo()[0].toDomain()
    }

    /**
     * 사용자 정보 로컬에 저장
     */
    override suspend fun insertUserInfoLocal(userInfoDto: UserInfoDto) {
        userDao.insertUserInfoLocal(userInfoDto.toData())
    }

}