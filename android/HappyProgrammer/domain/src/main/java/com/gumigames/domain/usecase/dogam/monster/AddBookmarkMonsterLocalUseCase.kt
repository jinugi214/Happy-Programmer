package com.gumigames.domain.usecase.dogam.monster

import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class AddBookmarkMonsterLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(monsterId: Int){
        dogamRepository.addBookmarkMonsterLocal(monsterId)
    }
}