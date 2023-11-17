package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.model.common.SkillDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class GetAllBookmarkSkillsLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(): List<SkillDto>{
        return dogamRepository.getAllBookmarkSkillsLocal()
    }
}