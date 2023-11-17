package com.gumigames.domain.usecase.preference

import com.gumigames.domain.repository.PreferenceRepository
import javax.inject.Inject

class SetAccessTokenUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    operator fun invoke(accessToken: String){
        preferenceRepository.setAccessToken(accessToken)
    }
}