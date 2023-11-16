package com.gumigames.domain.usecase.setting

import com.gumigames.domain.repository.PreferenceRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    operator fun invoke(){
        preferenceRepository.refreshPreference()
    }
}