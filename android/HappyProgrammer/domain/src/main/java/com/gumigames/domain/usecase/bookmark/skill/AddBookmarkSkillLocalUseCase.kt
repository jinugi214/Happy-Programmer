package com.gumigames.domain.usecase.bookmark.skill

import com.gumigames.domain.model.item.SkillDto
import com.gumigames.domain.repository.BookmarkRepository
import javax.inject.Inject

class AddBookmarkSkillLocalUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) {
    suspend operator fun invoke(skillDto: SkillDto){
        bookmarkRepository.addBookmarkSkillLocal(skillDto)
    }
}