package com.gumigames.data.repository

import com.gumigames.data.mapper.toDomain
import com.gumigames.data.service.MissionService
import com.gumigames.data.util.handleApi
import com.gumigames.domain.model.basic.BasicDto
import com.gumigames.domain.repository.MissionRepository
import okhttp3.MultipartBody

class MissionRepositoryImpl(
    private val missionService: MissionService
): MissionRepository {
    /**
     * 미션 사진 조회
     */
    override suspend fun getMissionPhotoResult(multipartBody: MultipartBody.Part): BasicDto{
        return handleApi { missionService.getMissionPhotoResult(multipartBody) }.toDomain()
    }
}