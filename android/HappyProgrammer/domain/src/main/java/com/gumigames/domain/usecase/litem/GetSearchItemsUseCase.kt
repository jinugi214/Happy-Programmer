package com.gumigames.domain.usecase.litem

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.ItemRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetSearchItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(keyword: String): List<ItemDto>{
        return getValueOrThrow { itemRepository.getSearchItems(keyword) }
    }
}