package com.gumigames.data.service

import com.gumigames.data.model.request.user.LoginRequest
import com.gumigames.data.model.response.basic.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    /**
     * 로그인
     */
    @POST("api/account/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    /**
     *
     */
}