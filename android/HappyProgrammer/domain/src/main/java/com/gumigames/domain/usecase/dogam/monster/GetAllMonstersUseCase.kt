package com.gumigames.domain.usecase.dogam.monster

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.model.item.MonsterDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetAllMonstersUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(): List<MonsterDto>{
        return getValueOrThrow { dogamRepository.getAllMonsters() }
    }
}