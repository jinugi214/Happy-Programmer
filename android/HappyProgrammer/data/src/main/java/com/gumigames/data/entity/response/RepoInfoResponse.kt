package com.moneyminions.mvvmtemplate.dto

data class RepoInfoResponse(
    val name: String,
    val description: String,
    val htmlUrl: String,
    val visibility: String
)