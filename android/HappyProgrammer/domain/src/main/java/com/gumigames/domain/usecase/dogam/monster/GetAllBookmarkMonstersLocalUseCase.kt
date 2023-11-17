package com.gumigames.domain.usecase.dogam.monster

import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class GetAllBookmarkMonstersLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(): List<MonsterDto>{
        return dogamRepository.getAllBookmarkMonstersLocal()
    }
}