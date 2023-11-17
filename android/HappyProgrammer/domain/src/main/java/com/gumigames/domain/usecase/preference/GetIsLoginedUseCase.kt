package com.gumigames.domain.usecase.preference

import com.gumigames.domain.repository.PreferenceRepository
import com.gumigames.domain.repository.UserRepository
import javax.inject.Inject

class GetIsLoginedUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    operator fun invoke(): Boolean{
        return preferenceRepository.getIsLogined()
    }
}