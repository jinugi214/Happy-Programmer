package com.gumigames.domain.usecase.preference

import com.gumigames.domain.repository.PreferenceRepository
import javax.inject.Inject

class SetIsAlreadyShowedDialogUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
){
    operator fun invoke(value: Boolean){
        preferenceRepository.setIsAlreadyShowedDialog(value)
    }
}