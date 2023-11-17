package com.gumigames.domain.usecase.dogam.skill

import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class DeleteBookmarkSkillLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(skillId: Int){
        dogamRepository.deleteBookmarkSkillLocal(skillId)
    }
}