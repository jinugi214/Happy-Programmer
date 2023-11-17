package com.gumigames.domain.usecase.preference

import com.gumigames.domain.repository.PreferenceRepository
import javax.inject.Inject

class SetPermissionRejectedUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
){
    operator fun invoke(key: String, value: Boolean){
        preferenceRepository.setPermissionRejected(key, value)
    }
}