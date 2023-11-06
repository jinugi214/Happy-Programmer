package com.gumigames.domain.repository

import com.gumigames.domain.model.basic.BasicDto
import okhttp3.MultipartBody

interface MissionRepository {
    /**
     * 미션 사진 조회
     */
    suspend fun getMissionPhotoResult(multipartBody: MultipartBody.Part): BasicDto
}