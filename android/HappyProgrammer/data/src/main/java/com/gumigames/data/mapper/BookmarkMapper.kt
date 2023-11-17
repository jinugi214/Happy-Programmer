package com.gumigames.data.mapper

import com.gumigames.data.model.response.bookmark.BookmarkResponse

fun BookmarkResponse.toDomain(): Boolean{
    return favorite
}