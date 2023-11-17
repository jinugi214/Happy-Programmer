package com.gumigames.domain.usecase.dogam.litem

import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class ToggleBookmarkItemUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(itemId: Int): Boolean{
        return getValueOrThrow { dogamRepository.toggleBookmarkItem(itemId) }
    }
}