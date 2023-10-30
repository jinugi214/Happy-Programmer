package com.gumigames.data.datasource.remote

import com.gumigames.data.service.GithubService
import com.gumigames.data.util.handleApi
import com.moneyminions.mvvmtemplate.dto.RepoResponse
import retrofit2.Response

class GithubDataSourceImpl(
    val githubService: GithubService
): GithubDataSource {
    override suspend fun getUserRepos(user: String): List<RepoResponse> {
        return handleApi { githubService.getUserRepos(user) }
    }
}