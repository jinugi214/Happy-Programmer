package com.gumigames.data.mapper

import com.example.domain.model.RepoDto
import com.moneyminions.mvvmtemplate.dto.RepoResponse

fun RepoResponse.toDomain(): RepoDto {
    return RepoDto(
        id, name, htmlUrl, url, gitUrl
    )
}