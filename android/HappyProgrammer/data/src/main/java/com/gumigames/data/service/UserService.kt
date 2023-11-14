package com.gumigames.data.service

import com.gumigames.data.model.request.user.LoginRequest
import com.gumigames.data.model.response.basic.AuthResponse
import com.gumigames.data.model.response.user.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    /**
     * 로그인
     */
    @POST("api/account/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>

    /**
     * 사용자 정보 조회
     */
    @GET("api/character/my")
    suspend fun getUserInfo(): Response<UserInfoResponse>
}