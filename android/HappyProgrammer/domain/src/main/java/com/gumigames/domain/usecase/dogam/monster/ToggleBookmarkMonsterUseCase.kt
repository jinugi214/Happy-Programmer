package com.gumigames.domain.usecase.dogam.monster

import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class ToggleBookmarkMonsterUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(monsterId: Int): Boolean{
        return getValueOrThrow { dogamRepository.toggleBookmarkMonster(monsterId) }
    }
}