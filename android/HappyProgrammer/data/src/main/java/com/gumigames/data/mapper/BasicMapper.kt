package com.gumigames.data.mapper

import com.gumigames.data.model.response.basic.BasicResponse
import com.gumigames.domain.model.basic.BasicDto


fun BasicResponse.toDomain(): BasicDto{
    return BasicDto(
        result = result
    )
}