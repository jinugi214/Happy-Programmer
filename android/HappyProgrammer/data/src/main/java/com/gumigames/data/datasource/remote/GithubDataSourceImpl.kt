package com.gumigames.data.datasource.remote

import com.gumigames.data.service.GithubService
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import retrofit2.Response

class GithubDataSourceImpl(
    val githubService: GithubService
): GithubDataSource {
    override suspend fun getUserRepos(user: String): Response<List<RepoResponse>> {
        return githubService.getUserRepos(user)
    }
}