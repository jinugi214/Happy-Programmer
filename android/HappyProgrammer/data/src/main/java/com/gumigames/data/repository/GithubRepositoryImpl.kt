package com.gumigames.data.repository

import com.example.domain.model.RepoDto
import com.gumigames.data.datasource.remote.GithubDataSource
import com.gumigames.data.mapper.toDomain
import com.gumigames.domain.util.NetworkThrowable
import com.gumigames.domain.repository.GithubRepository
import com.gumigames.domain.util.getValueOrThrow
import java.lang.Exception

class GithubRepositoryImpl(
    private val githubDataSource: GithubDataSource
): GithubRepository {
    override suspend fun getUserRepos(user: String): List<RepoDto> {
        return getValueOrThrow { githubDataSource.getUserRepos(user).map { it.toDomain() } }
    }
}