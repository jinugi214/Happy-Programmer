package com.gumigames.data.mapper

import com.gumigames.data.model.request.user.LoginRequest
import com.gumigames.domain.model.user.LoginDto

fun LoginDto.toData(): LoginRequest{
    return LoginRequest(
        username = id,
        password = password
    )
}