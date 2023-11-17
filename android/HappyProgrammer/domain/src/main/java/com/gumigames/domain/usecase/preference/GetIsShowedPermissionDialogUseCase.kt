package com.gumigames.domain.usecase.preference

import com.gumigames.domain.repository.PreferenceRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetIsShowedPermissionDialogUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
){
    operator fun invoke(key: String): Boolean{
        return preferenceRepository.getIsShowedPermissionDialog(key)
    }
}