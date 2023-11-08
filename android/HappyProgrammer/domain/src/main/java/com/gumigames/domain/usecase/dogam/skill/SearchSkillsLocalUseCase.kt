package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class SearchSkillsLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(keyword: String): List<SkillDto>{
        return dogamRepository.searchSkillsLocal(keyword)
    }
}