package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetAllSkillsUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(): List<SkillDto>{
        return getValueOrThrow { dogamRepository.getAllSkills() }
    }
}