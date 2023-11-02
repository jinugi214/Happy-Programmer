package com.gumigames.domain.usecase.bookmark.item

import com.gumigames.domain.model.item.ItemDto
import com.gumigames.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetAllBookmarkItemsLocalUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(): List<ItemDto>{
        return bookmarkRepository.getAllBookmarkItemLocal()
    }
}