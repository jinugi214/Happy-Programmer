package com.gumigames.domain.usecase.preference

import com.gumigames.domain.repository.PreferenceRepository
import javax.inject.Inject

class SetRefreshTokenUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    operator fun invoke(refreshToken: String){
        preferenceRepository.setRefreshToken(refreshToken)
    }
}