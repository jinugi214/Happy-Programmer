package com.gumigames.domain.usecase.dogam.monster

import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class InsertAllMonstersLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(monsterList: List<MonsterDto>){
        dogamRepository.insertAllMonstersLocal(monsterList)
    }
}