package com.gumigames.domain.usecase.dogam.litem

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetSearchItemsUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(keyword: String): List<ItemDto>{
        return getValueOrThrow { dogamRepository.getSearchItems(keyword) }
    }
}