package com.gumigames.data.mapper

import com.gumigames.data.model.response.basic.AuthResponse
import com.gumigames.data.model.response.basic.BasicResponse
import com.gumigames.domain.model.basic.AuthDto
import com.gumigames.domain.model.basic.BasicDto


fun BasicResponse.toDomain(): BasicDto{
    return BasicDto(
        result = result
    )
}

fun AuthResponse.toDomain(): AuthDto {
    return AuthDto(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}