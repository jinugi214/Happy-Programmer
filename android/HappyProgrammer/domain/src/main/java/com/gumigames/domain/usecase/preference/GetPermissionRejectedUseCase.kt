package com.gumigames.domain.usecase.preference

import com.gumigames.domain.repository.PreferenceRepository
import javax.inject.Inject

class GetPermissionRejectedUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
){
    operator fun invoke(key: String): Boolean{
        return preferenceRepository.getPermissionRejected(key)
    }
}