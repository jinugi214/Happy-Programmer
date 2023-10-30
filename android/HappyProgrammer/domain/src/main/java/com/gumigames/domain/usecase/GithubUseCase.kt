package com.gumigames.domain.usecase

import com.example.domain.model.RepoDto
import com.gumigames.domain.repository.GithubRepository
import com.gumigames.domain.util.NetworkThrowable
import com.gumigames.domain.util.getValueOrThrow
import javax.inject.Inject

class GithubUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(user: String): List<RepoDto>{
        return getValueOrThrow { githubRepository.getUserRepos(user) }
    }
}