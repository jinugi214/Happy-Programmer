package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.model.item.SkillDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetSearchSkillsUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(keyword: String): List<SkillDto>{
        return getValueOrThrow { dogamRepository.getSearchSkills(keyword) }
    }
}