package com.gumigames.domain.usecase.bookmark.monster

import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.repository.BookmarkRepository
import javax.inject.Inject

class AddBookmarkMonsterLocalUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(monsterDto: MonsterDto){
        bookmarkRepository.addBookmarkMonsterLocal(monsterDto)
    }
}