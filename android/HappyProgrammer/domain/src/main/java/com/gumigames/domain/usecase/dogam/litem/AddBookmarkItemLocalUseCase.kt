package com.gumigames.domain.usecase.dogam.litem

import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class AddBookmarkItemLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(itemId: Int){
        dogamRepository.addBookmarkItemLocal(itemId)
    }
}