package com.gumigames.domain.usecase.dogam.litem

import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class GetAllBookmarkItemsLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(): List<ItemDto>{
        return dogamRepository.getAllBookmarkItemsLocal()
    }
}