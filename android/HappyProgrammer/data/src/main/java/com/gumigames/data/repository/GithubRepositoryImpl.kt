package com.gumigames.data.repository

import com.example.domain.model.RepoDto
import com.gumigames.data.datasource.remote.GithubDataSource
import com.gumigames.data.mapper.toDomain
import com.gumigames.data.util.NetworkThrowable
import com.gumigames.data.util.handleApi
import com.gumigames.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val githubDataSource: GithubDataSource
): GithubRepository {
//    override suspend fun getUserRepos(user: String): List<RepoDto> {
//        return kotlin.runCatching {
//            githubDataSource.getUserRepos(user)
//        }.onSuccess {list ->
//            return list.body().map { it.toDomain() }
//        }.onFailure {throwable ->
//            throw throwable
//        }.getOrThrow()
//    }
}