package com.gumigames.domain.usecase.litem

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.ItemRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class AddBookmarkItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(itemDto: ItemDto){
        itemRepository.addBookmarkItem(itemDto)
    }
}