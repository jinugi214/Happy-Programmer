package com.gumigames.data.datasource.remote

import com.moneyminions.mvvmtemplate.dto.RepoResponse
import retrofit2.Response

interface GithubDataSource {
    suspend fun getUserRepos(user: String): List<RepoResponse>
}