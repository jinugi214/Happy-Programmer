package com.gumigames.domain.repository

import com.gumigames.domain.model.basic.AuthDto
import com.gumigames.domain.model.user.LoginDto
import com.gumigames.domain.model.user.UserInfoDto

interface UserRepository {
    suspend fun login(loginDto: LoginDto): AuthDto
    /**
     * 사용자 정보 조회
     */
    suspend fun getUserInfo(): UserInfoDto
    /**
     * 사용자 정보 로컬 조회
     */
    suspend fun getUserInfoLocal(): UserInfoDto
    /**
     * 사용자 정보 로컬에 저장
     */
    suspend fun insertUserInfoLocal(userInfoDto: UserInfoDto)
}