package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class InsertAllSkillsLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
){
    suspend operator fun invoke(skillList: List<SkillDto>){
        dogamRepository.insertAllSkillsLocal(skillList)
    }
}