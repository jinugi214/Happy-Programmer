package com.gumigames.domain.usecase.dogam.litem

import com.gumigames.domain.repository.DogamRepository
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class DeleteBookmarkItemLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(itemId: Int){
        dogamRepository.deleteBookmarkItemLocal(itemId)
    }
}