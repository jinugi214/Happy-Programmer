package com.gumigames.domain.usecase.dogam.litem

import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.repository.DogamRepository
import javax.inject.Inject

class InsertAllItemsLocalUseCase @Inject constructor(
    private val dogamRepository: DogamRepository
) {
    suspend operator fun invoke(itemList: List<ItemDto>){
        dogamRepository.insertAllItemsLocal(itemList)
    }
}