package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class ToggleBookmarkSkillUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(skillId: Int): Boolean{
        return getValueOrThrow { dogamRepository.toggleBookmarkSkill(skillId) }
    }
}