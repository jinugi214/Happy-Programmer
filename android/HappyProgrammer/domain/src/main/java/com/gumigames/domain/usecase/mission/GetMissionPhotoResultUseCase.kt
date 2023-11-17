package com.gumigames.domain.usecase.mission

import com.gumigames.domain.model.basic.BasicDto
import com.gumigames.domain.repository.MissionRepository
import com.gumigames.domain.util.getValueOrThrow
import okhttp3.MultipartBody
import javax.inject.Inject

class GetMissionPhotoResultUseCase @Inject constructor(
    private val missionRepository: MissionRepository
) {
    suspend operator fun invoke(multipartBody: MultipartBody.Part): BasicDto{
        return getValueOrThrow { missionRepository.getMissionPhotoResult(multipartBody) }
    }
}