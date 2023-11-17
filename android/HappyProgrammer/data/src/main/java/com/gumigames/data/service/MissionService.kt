package com.gumigames.data.service

import com.gumigames.data.model.response.basic.BasicResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface MissionService {

    /**
     * 미션 제출
     */
    @Multipart
    @POST("game-feature/picture")
    suspend fun getMissionPhotoResult(
        @Part multipartBody: MultipartBody.Part
    ): Response<BasicResponse>

}