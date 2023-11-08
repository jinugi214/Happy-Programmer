package com.gumigames.domain.usecase.dogam.monster

import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class SearchMonstersUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(keyword: String): List<MonsterDto>{
        return getValueOrThrow { dogamRepository.searchMonsters(keyword) }
    }
}