package com.gumigames.domain.usecase.litem

import com.example.domain.model.RepoDto
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.ItemRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GetAllItemsUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(): List<ItemDto>{
        return getValueOrThrow { itemRepository.getAllItems() }
    }
}