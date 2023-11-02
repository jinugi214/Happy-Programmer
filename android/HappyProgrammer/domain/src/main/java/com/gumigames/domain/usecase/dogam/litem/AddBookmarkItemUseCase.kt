package com.gumigames.domain.usecase.dogam.litem

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class AddBookmarkItemUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(itemDto: ItemDto){
        dogamRepository.addBookmarkItem(itemDto)
    }
}