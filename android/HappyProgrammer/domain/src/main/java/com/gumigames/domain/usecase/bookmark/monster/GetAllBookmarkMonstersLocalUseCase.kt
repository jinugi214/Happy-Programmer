package com.gumigames.domain.usecase.bookmark.monster

import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetAllBookmarkMonstersLocalUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(): List<MonsterDto>{
        return bookmarkRepository.getAllBookmarkMonstersLocal()
    }
}