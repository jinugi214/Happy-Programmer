package com.gumigames.data.repository

import com.example.domain.model.RepoDto
import com.gumigames.data.mapper.toDomain
import com.gumigames.data.service.GithubService
import com.gumigames.data.util.handleApi
import com.gumigames.domain.util.NetworkThrowable
import com.gumigames.domain.repository.GithubRepository
import com.gumigames.domain.util.getValueOrThrow
import java.lang.Exception

class GithubRepositoryImpl(
    private val githubService: GithubService
): GithubRepository {
    override suspend fun getUserRepos(user: String): List<RepoDto> {
        return handleApi { githubService.getUserRepos(user) }.map { it.toDomain() }
    }
}