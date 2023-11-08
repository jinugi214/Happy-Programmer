package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class GetAllSkillsLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(): List<SkillDto>{
        return dogamRepository.getAllSkillsLocal()
    }
}