package com.gumigames.domain.usecase.bookmark.skill

import com.gumigames.domain.model.item.SkillDto
import com.gumigames.domain.repository.BookmarkRepository
import javax.inject.Inject

class GetAllBookmarkSkillsLocalUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(): List<SkillDto>{
        return bookmarkRepository.getAllBookmarkSkillsLocal()
    }
}