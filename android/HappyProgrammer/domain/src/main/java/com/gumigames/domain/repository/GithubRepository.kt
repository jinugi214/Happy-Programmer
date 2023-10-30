package com.gumigames.domain.repository

import com.example.domain.model.RepoDto

interface GithubRepository {

    suspend fun getUserRepos(user: String): List<RepoDto>
}